package cn.davickk.rdi.essentials.general.enums;

public enum EGameTime {

    DAY(1000),
    NOON(6000),
    NIGHT(13000),
    MIDNIGHT(16000);
    public long time;
    EGameTime(long time){
        this.time=time;
    }
}
