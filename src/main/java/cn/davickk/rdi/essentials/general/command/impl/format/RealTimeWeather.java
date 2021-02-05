package cn.davickk.rdi.essentials.general.command.impl.format;

public final class RealTimeWeather {
    public final String status;
    public final String api_version;
    public final String api_status;
    public final String lang;
    public final String unit;
    public final long tzshift;
    public final String timezone;
    public final long server_time;
    public final double[] location;
    public final Result result;

    public RealTimeWeather(String status, String api_version, String api_status, String lang, String unit, long tzshift, String timezone, long server_time, double[] location, Result result){
        this.status = status;
        this.api_version = api_version;
        this.api_status = api_status;
        this.lang = lang;
        this.unit = unit;
        this.tzshift = tzshift;
        this.timezone = timezone;
        this.server_time = server_time;
        this.location = location;
        this.result = result;
    }

    public static final class Result {
        public final Alert alert;
        public final Realtime realtime;
        public final long primary;

        public Result(Alert alert, Realtime realtime, long primary){
            this.alert = alert;
            this.realtime = realtime;
            this.primary = primary;
        }

        public static final class Alert {
            public final String status;
            public final Content content[];

            public Alert(String status, Content[] content){
                this.status = status;
                this.content = content;
            }

            public static final class Content {
                public final String province;
                public final String status;
                public final String code;
                public final String description;
                public final long pubtimestamp;
                public final String city;
                public final String adcode;
                public final String regionId;
                public final double[] latlon;
                public final String county;
                public final String alertId;
                public final String request_status;
                public final String source;
                public final String title;
                public final String location;

                public Content(String province, String status, String code, String description, long pubtimestamp, String city, String adcode, String regionId, double[] latlon, String county, String alertId, String request_status, String source, String title, String location){
                    this.province = province;
                    this.status = status;
                    this.code = code;
                    this.description = description;
                    this.pubtimestamp = pubtimestamp;
                    this.city = city;
                    this.adcode = adcode;
                    this.regionId = regionId;
                    this.latlon = latlon;
                    this.county = county;
                    this.alertId = alertId;
                    this.request_status = request_status;
                    this.source = source;
                    this.title = title;
                    this.location = location;
                }
            }
        }

        public static final class Realtime {
            public final String status;
            public final float temperature;
            public final float humidity;
            public final float cloudrate;
            public final String skycon;
            public final double visibility;
            public final double dswrf;
            public final Wind wind;
            public final float pressure;
            public final float apparent_temperature;
            public final Precipitation precipitation;
            public final Air_quality air_quality;
            public final Life_index life_index;

            public Realtime(String status, float temperature, float humidity, float cloudrate, String skycon, float visibility, float dswrf, Wind wind, float pressure, float apparent_temperature, Precipitation precipitation, Air_quality air_quality, Life_index life_index){
                this.status = status;
                this.temperature = temperature;
                this.humidity = humidity;
                this.cloudrate = cloudrate;
                this.skycon = skycon;
                this.visibility = visibility;
                this.dswrf = dswrf;
                this.wind = wind;
                this.pressure = pressure;
                this.apparent_temperature = apparent_temperature;
                this.precipitation = precipitation;
                this.air_quality = air_quality;
                this.life_index = life_index;
            }

            public static final class Wind {
                public final float speed;
                public final float direction;

                public Wind(float speed, float direction){
                    this.speed = speed;
                    this.direction = direction;
                }
            }

            public static final class Precipitation {
                public final Local local;
                public final Nearest nearest;

                public Precipitation(Local local, Nearest nearest){
                    this.local = local;
                    this.nearest = nearest;
                }

                public static final class Local {
                    public final String status;
                    public final String datasource;
                    public final double intensity;

                    public Local(String status, String datasource, float intensity){
                        this.status = status;
                        this.datasource = datasource;
                        this.intensity = intensity;
                    }
                }

                public static final class Nearest {
                    public final String status;
                    public final double distance;
                    public final double intensity;

                    public Nearest(String status, float distance, float intensity){
                        this.status = status;
                        this.distance = distance;
                        this.intensity = intensity;
                    }
                }
            }

            public static final class Air_quality {
                public final float pm25;
                public final float pm10;
                public final float o3;
                public final float so2;
                public final float no2;
                public final float co;
                public final Aqi aqi;
                public final Description description;

                public Air_quality(float pm25, float pm10, float o3, float so2, float no2, float co, Aqi aqi, Description description){
                    this.pm25 = pm25;
                    this.pm10 = pm10;
                    this.o3 = o3;
                    this.so2 = so2;
                    this.no2 = no2;
                    this.co = co;
                    this.aqi = aqi;
                    this.description = description;
                }

                public static final class Aqi {
                    public final float chn;
                    public final float usa;

                    public Aqi(float chn, float usa){
                        this.chn = chn;
                        this.usa = usa;
                    }
                }

                public static final class Description {
                    public final String chn;
                    public final String usa;

                    public Description(String chn, String usa){
                        this.chn = chn;
                        this.usa = usa;
                    }
                }
            }

            public static final class Life_index {
                public final Ultraviolet ultraviolet;
                public final Comfort comfort;

                public Life_index(Ultraviolet ultraviolet, Comfort comfort){
                    this.ultraviolet = ultraviolet;
                    this.comfort = comfort;
                }

                public static final class Ultraviolet {
                    public final float index;
                    public final String desc;

                    public Ultraviolet(float index, String desc){
                        this.index = index;
                        this.desc = desc;
                    }
                }

                public static final class Comfort {
                    public final float index;
                    public final String desc;

                    public Comfort(float index, String desc){
                        this.index = index;
                        this.desc = desc;
                    }
                }
            }
        }
    }
}