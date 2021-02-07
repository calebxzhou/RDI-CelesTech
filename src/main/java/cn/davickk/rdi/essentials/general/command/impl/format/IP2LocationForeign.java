package cn.davickk.rdi.essentials.general.command.impl.format;

public final class IP2LocationForeign {
    public final String ip;
    public final String type;
    public final String continent_code;
    public final String continent_name;
    public final String country_code;
    public final String country_name;
    public final String region_code;
    public final String region_name;
    public final String city;
    public final String zip;
    public final double latitude;
    public final double longitude;
    public final Location location;

    public IP2LocationForeign(String ip, String type, String continent_code, String continent_name, String country_code, String country_name, String region_code, String region_name, String city, String zip, double latitude, double longitude, Location location){
        this.ip = ip;
        this.type = type;
        this.continent_code = continent_code;
        this.continent_name = continent_name;
        this.country_code = country_code;
        this.country_name = country_name;
        this.region_code = region_code;
        this.region_name = region_name;
        this.city = city;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public static final class Location {
        public final int geoname_id;
        public final String capital;
        public final Language languages[];
        public final String country_flag;
        public final String country_flag_emoji;
        public final String country_flag_emoji_unicode;
        public final String calling_code;
        public final boolean is_eu;

        public Location(int geoname_id, String capital, Language[] languages, String country_flag, String country_flag_emoji, String country_flag_emoji_unicode, String calling_code, boolean is_eu){
            this.geoname_id = geoname_id;
            this.capital = capital;
            this.languages = languages;
            this.country_flag = country_flag;
            this.country_flag_emoji = country_flag_emoji;
            this.country_flag_emoji_unicode = country_flag_emoji_unicode;
            this.calling_code = calling_code;
            this.is_eu = is_eu;
        }

        public static final class Language {
            public final String code;
            public final String name;
            public final String _native;

            public Language(String code, String name, String _native){
                this.code = code;
                this.name = name;
                this._native = _native;
            }
        }
    }
}