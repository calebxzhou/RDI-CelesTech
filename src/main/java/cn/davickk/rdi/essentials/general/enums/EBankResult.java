package cn.davickk.rdi.essentials.general.enums;

public enum EBankResult {
    OK(1),

    FAIL_NO_ENOUGH_$(11),

    ACCOUNT_NOT_EXIST(20),
    ALREADY_HAVE_ACCOUNT(21);
    private int rst;

    private EBankResult(int rst) {
        this.rst = rst;
    }

    public int get() {
        return rst;
    }

}
