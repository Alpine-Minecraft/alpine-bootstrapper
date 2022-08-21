package me.alpine.bootstrapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public enum Helper {
    WINDOWS("AppData" + File.separator + "Roaming" + File.separator + ".minecraft"),
    MAC("Library" + File.separator + "Application Support" + File.separator + "minecraft"),
    LINUX(".minecraft");

    private final String mc;

    Helper(String mc) {
        this.mc = File.separator + mc + File.separator;
    }

    public String getMc() {
        return System.getProperty("user.home") + this.mc;
    }

    public static Helper getOS() {
        String currentOS = System.getProperty("os.name").toLowerCase();
        if (currentOS.startsWith("windows")) {
            return WINDOWS;
        }
        if (currentOS.startsWith("mac")) {
            return MAC;
        }
        return LINUX;
    }

    public static String getMinecraftPath() {
        return getOS().getMc();
    }

    public static String getAlpineJarPath() {
        String s = File.separator;
        return getMinecraftPath() + s + "libraries" + s + "Alpine" + s + "alpine" + s + "release" + s + "alpine-release.jar";
    }

    public static long getFileSize(URL url) {
        try {
            return url.openConnection().getContentLengthLong();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}