package cn.davickk.rdi.essentials.general.enums;

public enum EBack {
    RECORD(1),
    READ(2);
    public final int opr;
    EBack(int opr) {
        this.opr= opr;
    }

}
