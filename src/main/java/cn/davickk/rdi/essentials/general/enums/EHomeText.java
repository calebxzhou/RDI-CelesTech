package cn.davickk.rdi.essentials.general.enums;

public enum EHomeText {
    LISTHOME(EColor.GOLD.code+"[�鿴���еļ�]","/listhome"),
    HOME(EColor.GOLD.code+"[�ص�%s]","/home %s"),
    SETHOME_DEFAULT(EColor.GOLD.code+"[�������ü�]","/sethome a"),
    NOT_FOUND("û���ҵ�%s...."),
    ACTIVATE(EColor.GOLD.code+"[���̼���%s]","/activhome %s"),
    DEACTIVATE(EColor.PURPLE.code+"[ȡ������%s]","/activhome %s"),
    SHARE(EColor.GOLD.code+"[����]","/sharehome %s %p"),
    IMPORT(EColor.AQUA.code+"[��Home2.0����]","/fromoldhome"),
    DELETE(EColor.PURPLE.code+"[ɾ��%s]","/delhome %s"),
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
