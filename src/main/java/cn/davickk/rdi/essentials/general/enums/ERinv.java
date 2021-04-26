package cn.davickk.rdi.essentials.general.enums;

public enum ERinv {
    UPLOAD(EColor.AQUA.code+"[放入]  ","/rinv put"),
    UPLOAD_HOVER("将背包中的物品全部放入至云仓库"),
    DOWNLOAD(EColor.BRIGHT_GREEN.code+"  [取出]  ","/rinv get"),
    DOWNLOAD_NOW(EColor.BRIGHT_GREEN.code+"[立刻取出]","/rinv get"),
    DOWNLOAD_HOVER("取出云仓库中的物品，放到背包"),
    LIST(EColor.GOLD.code+"  [查看]  ","/rinv list"),
    LIST_HOVER("看看我的云仓库中都有什么"),
    LIST_LOOK(EColor.GOLD.code+"[都有什么?]","/rinv list"),
    DELETE(EColor.DARK_RED.code+EColor.BOLD+"[全部删除:/rinvshanchudelete]");
    public final String text;
    public final String cmd;

    ERinv(String text) {
        this.text= text;
        this.cmd="";
    }
    ERinv(String text, String cmd) {
        this.text=text;
        this.cmd=cmd;
    }

}
