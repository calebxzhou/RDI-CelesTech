package cn.davickk.rdi.essentials.general.enums;
public enum EWeather {

    CLEAR_DAY(EColor.GOLD.code+"��[����]"+EColor.RESET.code, 0),
    CLEAR_NIGHT(EColor.GOLD.code+"��[ҹ��]"+EColor.RESET.code, 1),
    PARTLY_CLOUDY_DAY("����[����]", 2),
    PARTLY_CLOUDY_NIGHT("����[ҹ��]", 3),
    CLOUDY(EColor.GRAY.code+"��"+EColor.RESET.code, 4),

    LIGHT_HAZE("����", 5),
    MODERATE_HAZE("����", 6),
    HEAVY_HAZE("����", 7),

    LIGHT_RAIN(EColor.ORANGE.code+"С��"+EColor.RESET.code, 8),
    MODERATE_RAIN(EColor.ORANGE.code+"����"+EColor.RESET.code, 9),
    HEAVY_RAIN(EColor.ORANGE.code+EColor.BOLD.code+"����"+EColor.RESET.code, 10),
    STORM_RAIN(EColor.ORANGE.code+EColor.BOLD.code+"����"+EColor.RESET.code, 11),

    FOG(EColor.GRAY.code+"��", 12),

    LIGHT_SNOW("Сѩ", 13),
    MODERATE_SNOW("��ѩ", 14),
    HEAVY_SNOW(EColor.BOLD.code+"��ѩ"+EColor.RESET.code, 15),
    STORM_SNOW(EColor.BOLD.code+"��ѩ"+EColor.RESET.code, 16),

    DUST("����", 17),
    SAND("ɳ��", 18),
    WIND("���", 19);
    private String name;
    private int index;

    // ���췽��
    private EWeather(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
