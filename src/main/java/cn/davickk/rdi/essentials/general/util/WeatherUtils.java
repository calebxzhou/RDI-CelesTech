package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.command.impl.format.IP2Location;
import cn.davickk.rdi.essentials.general.thread.weather.WeatherRequestThread;
import com.google.gson.Gson;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherUtils {
    public static IP2Location getLocationFromIP(String ip) {
        String httpurl = "https://apis.map.qq.com/ws/location/v1/ip?ip=" + ip + "&key=" + HttpUtils.tencentLbsKey;
        String locationResult = HttpUtils.doGet(httpurl);
        System.out.println(locationResult);
        return new Gson().fromJson(locationResult, IP2Location.class);
    }

    public static void sendWeatherToPlayer(String ip, ServerPlayerEntity player) {
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(new WeatherRequestThread(ip, player));
    }
    public static void sendRealTimeWeatherToPlayer()
    {

    }
}
