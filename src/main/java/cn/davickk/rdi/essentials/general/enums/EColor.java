package cn.davickk.rdi.essentials.general.enums;

public enum EColor {
    BLACK("¡ì0"),
    DARK_BLUE("¡ì1"),
    DARK_GREEN("¡ì2"),
    DARK_AQUA("¡ì3"),
    DARK_RED("¡ì4"),
    PURPLE("¡ì5"),
    ORANGE("¡ì6"),
    GRAY("¡ì7"),
    DARK_GRAY("¡ì8"),
    INDIGO("¡ì9"),
    BRIGHT_GREEN("¡ìa"),
    AQUA("¡ìb"),
    RED("¡ìc"),
    PINK("¡ìd"),
    YELLOW("¡ìe"),
    GOLD("¡ìe"),
    WHITE("¡ìf"),
    MAGIC("¡ìk"),
    BOLD("¡ìl"),
    STRIKE("¡ìm"),
    UNDERLINE("¡ìn"),
    ITALIC("¡ìo"),
    RESET("¡ìr");

    public final String code;

    EColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
