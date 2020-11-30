package me.MarioML.RocketClicker.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HWID {

    public static String getHwid() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String id = "";
        final String main = (System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME")
                + System.getProperty("user.name")).trim();

        final byte[] bytes = main.getBytes("UTF-8");
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] md5 = md.digest(bytes);
        int i = 0;

        for (final byte b : md5) {
            id += Integer.toHexString((b & 0xFF) | 0x100).substring(0, 3);
            if (i != md5.length - 1) {
                id += "-";
            }
            i++;
        }

        System.out.println(id);
        return id;
    }

    public static String getMotherboardSerial() {
        String result = "";
        try {
            File file = File.createTempFile("GetMBSerial",".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs =
                    "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                            + "Set colItems = objWMIService.ExecQuery _ \n"
                            + "   (\"Select * from Win32_ComputerSystemProduct\") \n"
                            + "For Each objItem in colItems \n"
                            + "    Wscript.Echo objItem.IdentifyingNumber \n"
                            + "Next \n";

            fw.write(vbs);
            fw.close();
            Process gWMI = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(gWMI.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
                System.out.println(line);
            }
            input.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        result = result.trim();
        return result;
    }
}
