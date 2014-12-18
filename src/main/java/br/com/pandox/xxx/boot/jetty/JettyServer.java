package br.com.pandox.xxx.boot.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class JettyServer {

    private static final int DEFAULT_PORT = 15081;
    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_LOCATION = "br.com.pandox.xxx.boot";
    private static final String MAPPING_URL = "/*";
    private static final String DEFAULT_PROFILE = "dev";

    public static void main(String[] args) throws Exception {
        Server server = new Server(DEFAULT_PORT);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
        server.join();
    }

    private static WebAppContext getServletContextHandler(WebApplicationContext context) throws
        IOException {
        WebAppContext contextHandler = new WebAppContext();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase("src/main/webapp/");
//        contextHandler.setAttribute("log4jConfigLocation", "log4j-debug.xml");
//        System.setProperty("log4jConfigLocation", "log4j-debug.xml");
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }
}
