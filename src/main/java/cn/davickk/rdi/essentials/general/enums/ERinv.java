package cn.davickk.rdi.essentials.general.enums;

public enum ERinv {
    UPLOAD(EColor.AQUA.code+"[����]  ","/rinvput"),
    UPLOAD_HOVER("�������е���Ʒȫ���������Ʋֿ�"),
    DOWNLOAD(EColor.BRIGHT_GREEN.code+"  [ȡ��]  ","/rinvget"),
    DOWNLOAD_NOW(EColor.BRIGHT_GREEN.code+"[����ȡ��]","/rinvget"),
    DOWNLOAD_HOVER("ȡ���Ʋֿ��е���Ʒ���ŵ�����"),
    LIST(EColor.GOLD.code+"  [�鿴]  ","/rinvls"),
    LIST_HOVER("�����ҵ��Ʋֿ��ж���ʲô"),
    LIST_LOOK(EColor.GOLD.code+"[����ʲô?]","/rinvls"),
    DELETE(EColor.DARK_RED.code+EColor.BOLD+"[ȫ��ɾ��:/rinvshanchudelete]");
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
