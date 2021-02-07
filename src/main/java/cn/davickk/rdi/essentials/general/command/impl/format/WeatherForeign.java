package cn.davickk.rdi.essentials.general.command.impl.format;
public final class WeatherForeign {
    public final Coord coord;
    public final Weather weather[];
    public final String base;
    public final Main main;
    public final Wind wind;
    public final Clouds clouds;
    public final long dt;
    public final Sys sys;
    public final long timezone;
    public final long id;
    public final String name;
    public final long cod;

    public WeatherForeign(Coord coord, Weather[] weather, String base, Main main, Wind wind, Clouds clouds, long dt, Sys sys, long timezone, long id, String name, long cod){
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public static final class Coord {
        public final double lon;
        public final double lat;

        public Coord(double lon, double lat){
            this.lon = lon;
            this.lat = lat;
        }
    }

    public static final class Weather {
        public final long id;
        public final String main;
        public final String description;
        public final String icon;

        public Weather(long id, String main, String description, String icon){
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }

    public static final class Main {
        public final double temp;
        public final double feels_like;
        public final double temp_min;
        public final double temp_max;
        public final double pressure;
        public final double humidity;

        public Main(double temp, double feels_like, double temp_min, double temp_max, double pressure, double humidity){
            this.temp = temp;
            this.feels_like = feels_like;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.humidity = humidity;
        }
    }

    public static final class Wind {
        public final double speed;
        public final double deg;

        public Wind(double speed, double deg){
            this.speed = speed;
            this.deg = deg;
        }
    }

    public static final class Clouds {
        public final long all;

        public Clouds(long all){
            this.all = all;
        }
    }

    public static final class Sys {
        public final long type;
        public final long id;
        public final double message;
        public final String country;
        public final long sunrise;
        public final long sunset;

        public Sys(long type, long id, double message, String country, long sunrise, long sunset){
            this.type = type;
            this.id = id;
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }
    }
}