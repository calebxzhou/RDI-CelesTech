package cn.davickk.rdi.essentials.general.enums;

public enum EHomeText {
    LISTHOME(EColor.GOLD.code+"[查看所有的家]","/listhome"),
    HOME(EColor.GOLD.code+"[回到%s]","/home %s"),
    SETHOME_DEFAULT(EColor.GOLD.code+"[立刻设置家]","/sethome a"),
    NOT_FOUND("没有找到%s...."),
    ACTIVATE(EColor.GOLD.code+"[立刻激活%s]","/activhome %s"),
    DEACTIVATE(EColor.PURPLE.code+"[取消激活%s]","/activhome %s"),
    SHARE(EColor.GOLD.code+"[分享]","/sharehome %s %p"),
    IMPORT(EColor.AQUA.code+"[从Home2.0导入]","/fromoldhome"),
    DELETE(EColor.PURPLE.code+"[删除%s]","/delhome %s"),
    RENAME("","/renamehome %s %s2");
    public final String text;
    public final String cmd;

    EHomeText(String text) {
        this.text= text;
        this.cmd="";
    }
    EHomeText(String text,String cmd) {
        this.text=text;
        this.cmd=cmd;
    }

}
