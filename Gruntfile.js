module.exports = function (grunt) {

    grunt.loadNpmTasks('grunt-contrib-htmlmin');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-cdn');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-filerev');
    grunt.loadNpmTasks('grunt-rev');
    grunt.loadNpmTasks('grunt-usemin');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-compress');
    grunt.loadNpmTasks('grunt-strip');


    // Define the configuration for all the tasks
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        clean: {
            build: {
                src: ["dist", "tmp", ".tmp", "dest", "src"]
            },

            trash: {
                src: ["tmp", ".tmp", "dest", "src"]
            }
        },

        useminPrepare: {
            src: ['app/includes/head.html', 'dist/includes/head.html'],
            options: {
                dest: 'dist'
            }
        },

        usemin: {
            html: 'dist/includes/head.html',
            options: {
                assetsDirs: ['dist']
            }
        },

        rev: {
            options: {
                encoding: 'utf8',
                algorithm: 'md5',
                length: 8
            },
            dist: {
                files: {
                    src: [
                        'dist/**/*.{js,css,img,jpg,gif,png}',
                    ]
                }
            }
        },

        copy: {
            dist: {
                files: [
                    {
                        cwd: 'app/',
                        expand: true,
                        src: ['**/*.html', '**/*.json', '**/*.png', '**/*.jpg', '**/*.ico'],
                        dest: 'dist'
                    }
                ]
            }
        },

        htmlmin: {
            dist: {
                options: {
                    collapseWhitespace: true,
                    collapseBooleanAttributes: true,
                    removeCommentsFromCDATA: true,
                    removeOptionalTags: true
                },
                files: [{
                    expand: true,
                    cwd: 'dist',
                    src: ['**/*.html'],
                    dest: 'dist'
                }]
            }
        },

        compress: {
            main: {
                options: {
                    archive: 'site.zip'
                },
                files: [{
                    src: ['**'],
                    dest: 'zip/',
                    cwd: 'dist/',
                    expand: true
                }]
            }
        },

        // STRIP CONSOLE.log
        strip: {
            main: {
                src: 'dist/resources/**/*.js',
                options: {
                    inline: true
                }
            }
        },

        concat: {
            analytics: {
                src: ['dist/includes/analytics.html', 'dist/includes/head.html'],
                dest: 'dist/includes/head.html'

            }
        }
    });

    grunt.registerTask('dist', [
        'clean:build',
        'useminPrepare',
        'concat:generated',
        'cssmin:generated',
        'uglify:generated',
        'rev',
        'copy:dist',
        'usemin',
        'htmlmin:dist',
        'strip:main',
        'clean:trash'
    ]);

    grunt.registerTask('delivery', [
        'dist',
        'concat:analytics'
    ]);
};
