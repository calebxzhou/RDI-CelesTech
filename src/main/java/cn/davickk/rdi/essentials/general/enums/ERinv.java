package cn.davickk.rdi.essentials.general.enums;

public enum ERinv {
    UPLOAD(EColor.AQUA.code+"[����]  ","/rinv put"),
    UPLOAD_HOVER("�������е���Ʒȫ���������Ʋֿ�"),
    DOWNLOAD(EColor.BRIGHT_GREEN.code+"  [ȡ��]  ","/rinv get"),
    DOWNLOAD_NOW(EColor.BRIGHT_GREEN.code+"[����ȡ��]","/rinv get"),
    DOWNLOAD_HOVER("ȡ���Ʋֿ��е���Ʒ���ŵ�����"),
    LIST(EColor.GOLD.code+"  [�鿴]  ","/rinv list"),
    LIST_HOVER("�����ҵ��Ʋֿ��ж���ʲô"),
    LIST_LOOK(EColor.GOLD.code+"[����ʲô?]","/rinv list"),
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
