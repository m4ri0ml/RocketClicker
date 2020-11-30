package me.MarioML.RocketClicker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class VersionChecker {
    public static boolean ic;

    public static String getVersion() {
        String version = "";

        try {
            URL url = new URL("https://pastebin.com/raw/3AfQdYt4");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            version = br.readLine();

            br.close();
        }catch (IOException exc) {
            VersionChecker.ic = false;
            exc.printStackTrace();
        }

        return version;
    }
}
