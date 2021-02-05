package cn.davickk.rdi.essentials.general.enums;

public enum EDeathItemReq {
    RECORD(1),
    READ(2);
    public final int opr;
    EDeathItemReq(int opr) {
        this.opr= opr;
    }

}
