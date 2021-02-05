package cn.davickk.rdi.essentials.general.enums;

public enum EWorld {
    OVERWORLD("minecraft:overworld"),
    THE_NETHER("minecraft:the_nether"),
    THE_END("minecraft:the_end");
    public final String dim;
    EWorld(String text) {
        this.dim= text;
    }

}
