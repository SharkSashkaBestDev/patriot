package com.asoft.patriot;

import org.apache.commons.lang3.StringUtils;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;
import java.net.URL;

public class Main {

    private static final int PAUSE_BETWEEN_IMG_CLICK = 500;
    private static final String ruFlagName = "rus_flag.png",
                                uaFlagName = "ua_flag.png";

    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();

        try {
            URL ruFlagURL = classLoader.getResource(ruFlagName),
                uaFlagURL = classLoader.getResource(uaFlagName);

            if (ruFlagURL == null || StringUtils.isEmpty(ruFlagURL.getFile()))
                throw new Exception("The first image for comparison not found!");

            if (uaFlagURL == null || StringUtils.isEmpty(uaFlagURL.getFile()))
                throw new Exception("The second image for comparison not found!");

            String ruFlagPath = new File(ruFlagURL.getFile()).getAbsolutePath(),
                   uaFlagPath = new File(uaFlagURL.getFile()).getAbsolutePath();

            Screen screen = new Screen();
            Pattern ruPattern = new Pattern(ruFlagPath);
            Match ruMatch = screen.find(ruPattern);

            int ruFlagX = ruMatch.x,
                ruFlagY = ruMatch.y;

            String ruFlagCoordinates = String.format("The first image coordinates: x=%s; y=%s", ruFlagX, ruFlagY);
            System.out.println(ruFlagCoordinates);

            ruMatch.click();

            Thread.sleep(PAUSE_BETWEEN_IMG_CLICK);

            Pattern uaPattern = new Pattern(uaFlagPath);
            Match uaMatch = screen.find(uaPattern);

            uaMatch.click();

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
