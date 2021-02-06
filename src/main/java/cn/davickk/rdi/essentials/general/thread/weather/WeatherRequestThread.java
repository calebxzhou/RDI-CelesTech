package cn.davickk.rdi.essentials.general.thread.weather;

import cn.davickk.rdi.essentials.general.command.impl.format.IP2Location;
import cn.davickk.rdi.essentials.general.command.impl.format.RealTimeWeather;
import cn.davickk.rdi.essentials.general.command.impl.format.Weather;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EWeather;
import cn.davickk.rdi.essentials.general.util.HttpUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WeatherUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

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

                TextUtils.sendChatMessage(player, "�޷���ȡ����Ԥ��������ʾĬ�ϳ��е�����Ԥ����");
                if(player.getServer().isDedicatedServer())
                    return;
                else
                    loca = WeatherUtils.getLocationFromIP("202.107.26.39");
            }
            double lati = loca.result.location.lat;//γ��
            double longi = loca.result.location.lng;//����
            String city = "";
            String district = "";
            //String province = loca.result.ad_info.province;//ʡ
            city = loca.result.ad_info.city.replace("��", "");//��
            district = loca.result.ad_info.district.replace("��", "")
                    .replace("��", "");

            String weatherJsonData = HttpUtils.doGet("https://api.caiyunapp.com/v2.5/" + HttpUtils.caiyunWeatherKey
                    + "/" + longi + "," + lati + "/daily.json");
            String weatherRTJsonData = HttpUtils.doGet("https://api.caiyunapp.com/v2.5/" + HttpUtils.caiyunWeatherKey
                    + "/" + longi + "," + lati + "/realtime.json?alert=true");//��ȡ����Ԥ��json
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
                TextUtils.sendChatMessage(player,EColor.ORANGE.code+fullMsg.replace("����"," - "));
            }
            String airQ=rtwe.result.realtime.air_quality.description.chn;
            int aqi=(int)rtwe.result.realtime.air_quality.aqi.chn;
            float tempNow=rtwe.result.realtime.temperature;
            int hour = LocalTime.now().getHour();


            String msgCityTemp=city+"-"+district+EColor.BRIGHT_GREEN.code+" ���� "+EColor.RESET.code+(int)tempNow+"�� ";
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
            String msgAir="�������� "+airQ+"("+aqi+")";
            String msgLine1=msgCityTemp.concat(msgAir);
            String msgLine2 = EColor.AQUA.code+ "���� " + weatherState.getName() + " "
                    + lowTmp + " ~ " + hiTmp + "�� ʪ��" + humid + "% ��ˮ��" + preci + "%";
            String msgLine3 = "���� " + weatherState.getName() + " "
                    + lowTmp2 + " ~ " + hiTmp2 + "�� ʪ��" + humid2 + "% ��ˮ��" + preci2 + "% ";
            //xx(��) 2020��x��x�� �� xx~xxC ʪ��xx% ��ˮ��xx%
            TextUtils.sendChatMessage(player, msgLine1);
            TextUtils.sendChatMessage(player, msgLine2);
            TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[�򿪿յ��˵�]","/rkd","/rkd");

            if(hour>=17)
                TextUtils.sendChatMessage(player, msgLine3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
