package cn.davickk.rdi.essentials.general.enums;

public enum EHomeResult {

    OK(1),

    NO_ENOUGH_XP(11),

    HOME_ALREADY_EXIST(21),
    HOME_NOT_EXIST(22),
    HOME_REACHED_MAX_AMOUNT(23),
    HOME_NOT_ACTIVE(24);
    private int rst;

    private EHomeResult(int rst) {
        this.rst = rst;
    }

    public int get() {
        return rst;
    }
}
