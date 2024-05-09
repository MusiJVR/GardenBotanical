package net.gardenbotanical.util;


import net.minecraft.nbt.NbtCompound;

public class ColorUtils {
    public static int blendColors(int color1, int color2) {

        int red1 = (color1 >> 16) & 0xFF;
        int green1 = (color1 >> 8) & 0xFF;
        int blue1 = color1 & 0xFF;

        int red2 = (color2 >> 16) & 0xFF;
        int green2 = (color2 >> 8) & 0xFF;
        int blue2 = color2 & 0xFF;

        return ((red1 + red2) / 2 << 16) | ((green1 + green2) / 2 << 8) | ((blue1 + blue2) / 2);
    }

    public static int checkColorNbt(NbtCompound nbt, int defaultColor) {
        if (nbt.get("color") != null) {
            return nbt.getInt("color");
        } else {
            return defaultColor;
        }
    }
}