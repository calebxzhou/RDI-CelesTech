package cn.davickk.rdi.essentials.general.enums;

public enum EGeneral {
    OVERWORLD("overworld"),
    THE_NETHER("the_nether"),
    THE_END("the_end"),
    LOADING("->....");
    public final String text;
    EGeneral(String text) {
        this.text= text;
    }

}
