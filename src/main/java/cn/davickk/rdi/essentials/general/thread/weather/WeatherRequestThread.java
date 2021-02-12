package cn.davickk.rdi.essentials.general.thread.weather;

import cn.davickk.rdi.essentials.general.format.*;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EWeather;
import cn.davickk.rdi.essentials.general.format.weather.RealTimeWeather;
import cn.davickk.rdi.essentials.general.format.weather.Weather;
import cn.davickk.rdi.essentials.general.format.weather.WeatherForeign;
import cn.davickk.rdi.essentials.general.util.HttpUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WeatherUtils;
import com.google.gson.Gson;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.time.LocalTime;

public class WeatherRequestThread extends Thread {
    private String ip;
    private ServerPlayerEntity player;

    public WeatherRequestThread(String ip, ServerPlayerEntity player) {
        this.ip = ip;
        this.player = player;
    }

    public void run() {
        try {
            IP2Location loca = WeatherUtils.getLocationFromIP(ip);
            if (loca.status != 0) {

                TextUtils.sendChatMessage(player, "无法获取天气预报，将显示默认城市的天气预报。");
                if(player.getServer().isDedicatedServer())
                    return;
                else
                    loca = WeatherUtils.getLocationFromIP("202.107.26.39");
            }
            String nation=loca.result.ad_info.nation;
            double lati;//纬度
            double longi;//经度
            String province="";
            String city = "";
            String district = "";
            String weatherJsonData;
            String weatherRTJsonData;
            //String province = loca.result.ad_info.province;//省
            if(nation.equals("中国"))
            {
                city = loca.result.ad_info.city.replace("市", "");//市
                district = loca.result.ad_info.district.replace("区", "")
                        .replace("市", "");
                lati = loca.result.location.lat;
                longi = loca.result.location.lng;
                weatherJsonData = HttpUtils.doGet("https://api.caiyunapp.com/v2.5/" + HttpUtils.caiyunWeatherKey
                        + "/" + longi + "," + lati + "/daily.json");
                weatherRTJsonData = HttpUtils.doGet("https://api.caiyunapp.com/v2.5/" + HttpUtils.caiyunWeatherKey
                        + "/" + longi + "," + lati + "/realtime.json?alert=true");//获取天气预报json
                Weather we = new Gson().fromJson(weatherJsonData, Weather.class);
                RealTimeWeather rtwe=new Gson().fromJson(weatherRTJsonData,RealTimeWeather.class);
                Weather.Result.Daily daily=we.result.daily;
                EWeather weatherState = EWeather.valueOf(daily.skycon[0].value);//daily.skycon[0].value;

                int lowTmp = (int) Math.round(daily.temperature[0].min);
                int hiTmp = (int) Math.round(daily.temperature[0].max);
                int preci = (int) Math.round(daily.precipitation[0].max * 100);
                int humid = (int) Math.round(daily.humidity[0].min * 100);
                int lowTmp2 = (int) Math.round(daily.temperature[1].min);
                int hiTmp2 = (int) Math.round(daily.temperature[1].max);
                int preci2 = (int) Math.round(daily.precipitation[1].max * 100);
                int humid2 = (int) Math.round(daily.humidity[1].min * 100);


                int alertLength=rtwe.result.alert.content.length;
                for(int i=0;i<alertLength;++i)
                {
                    //SEND MESSAGE To PLAYER
                    RealTimeWeather.Result.Alert.Content cont=rtwe.result.alert.content[i];
                    String title=cont.title;
                    //String status=cont.status;
                    String desp=cont.description;
                    String fullMsg=title+desp;
                    TextUtils.sendChatMessage(player,EColor.ORANGE.code+fullMsg.replace("发布"," - "));
                }
                String airQ=rtwe.result.realtime.air_quality.description.chn;
                int aqi=(int)rtwe.result.realtime.air_quality.aqi.chn;
                float tempNow=rtwe.result.realtime.temperature;
                int hour = LocalTime.now().getHour();


                String msgCityTemp=city+"-"+district+EColor.BRIGHT_GREEN.code+" 现在 "+EColor.RESET.code+(int)tempNow+"° ";
                if(aqi<50)
                    airQ=EColor.BRIGHT_GREEN.code+airQ;
                else if(aqi>50 && aqi<100)
                    airQ=EColor.AQUA.code+airQ;
                else if(aqi>100 && aqi<150)
                    airQ=EColor.ORANGE.code+airQ;
                else if(aqi>150 && aqi<200)
                    airQ=EColor.PINK.code+airQ;
                else if(aqi>200 && aqi<300)
                    airQ=EColor.PURPLE.code+airQ;
                else if(aqi>300)
                    airQ=EColor.DARK_RED.code+airQ;
                String msgAir="空气质量 "+airQ+"("+aqi+")";
                String msgLine1=msgCityTemp.concat(msgAir);
                String msgLine2 = EColor.AQUA.code+ "今天 " + weatherState.getName() + " "
                        +EColor.RESET.code+ lowTmp + " ~ " + hiTmp + "° 湿度" + humid + "% 降水率" + EColor.BRIGHT_GREEN.code+preci + "%";
                String msgLine3 = "明天 " + weatherState.getName() + " "
                        + lowTmp2 + " ~ " + hiTmp2 + "° 湿度" + humid2 + "% 降水率" + preci2 + "% ";
                //xx(市) 2020年x月x日 晴 xx~xxC 湿度xx% 降水率xx%
                TextUtils.sendChatMessage(player, msgLine1);
                TextUtils.sendChatMessage(player, msgLine2);
                if(hour>=17)
                    TextUtils.sendChatMessage(player, msgLine3);
            }else {
                IP2LocationForeign locaf=WeatherUtils.getLocationFromForeignIP(ip);
                city = locaf.city;
                province=locaf.region_name;
                nation=locaf.country_code;
                lati=locaf.latitude;
                longi=locaf.longitude;
                weatherJsonData=HttpUtils.doGet("https://api.openweathermap.org/data/2.5/weather?lat="
                        +lati+"&lon="+longi+"&appid="+HttpUtils.openWeatherKey);
                WeatherForeign we=new Gson().fromJson(weatherJsonData,WeatherForeign.class);
                String msgLine1=city+","+province+","+nation+"";
                TextUtils.sendChatMessage(player, msgLine1);
                String msgLine2 = EColor.BRIGHT_GREEN.code+" Now "+EColor.RESET.code+(int)we.main.temp+"° "+EColor.AQUA.code+ "Today " + we.weather[0].main +EColor.RESET.code+ " "
                        + we.main.temp_min + " ~ " + we.main.temp_max + "° Humid " + we.main.humidity + "%";

                TextUtils.sendChatMessage(player, msgLine2);
            }




            TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[打开空岛菜单]","/rkd","/rkd");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
