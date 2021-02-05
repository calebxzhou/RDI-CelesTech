package cn.davickk.rdi.essentials.general.util;

import java.util.Random;

public class RandomUtils {
    public static int generateRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
