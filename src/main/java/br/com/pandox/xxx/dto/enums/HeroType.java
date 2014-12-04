package br.com.pandox.xxx.dto.enums;

public enum HeroType {

//    DK = 16
//    ELF = 32
//    WIZ = 0
//    MG = 48
//    DL = 64
//    SUM = 80
//    RF = 96

    DK(16, "Cavaleiro Negro", "dk"),
    ELF(32, "Elfo", "elf"),
    DW(0, "Mago Negro", "dw"),
    MG(48, "Gladiator Mágico", "mg"),
    DL(64, "Senhor Negro", "dl"),
    SU(80, "Invocador", "su"),
    RF(96, "Lutador de Fúria", "rf"),

    SM(1, "Mestre da Alma", "dw"),
    BK(17, "Guerreiro da espada", "dk"),
    ME(33, "Mestre do Elfo", "elf"),
    BS(81, "Mestre do Elfo", "su"),

    GM(2, "Mestre da Alma", "dw"),
    BM(18, "Mestre da Espada", "dk"),
    HE(34, "Mestre do Elfo", "elf"),
    DMS(82, "Mestre do Elfo", "su"),

    DM(49, "Mestre do duelo", "mg"),
    DM_2(50, "Mestre do duelo", "mg"),
    LE(65, "Imperador", "dl"),
    LE_2(66, "Imperador", "dl");






//    SM(0),
//    SU(80),
//    RF(96),
//    DL(64),
//    MG(48);

    private int type;
    private String desc;
    private String img;

    HeroType(int type, String desc, String img) {

        this.type = type;
        this.desc = desc;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static HeroType valueOf(int type) {
        for (HeroType heroType : HeroType.values()) {
            if (heroType.type == type) {
                return heroType;
            }
        }

        return HeroType.DK;
    }


}
