package jortoo.muddify.utils.text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TextHelper {


    public static String miniText(String text) {
        if (text == null) return "";

        String plain = "abcdefghijklmnopqrstuvwxyz";
        String caps  = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀsᴛᴜᴠᴡxʏᴢ";

        StringBuilder builder = new StringBuilder();
        boolean inTag = false;

        for (char c : text.toCharArray()) {

            if (c == '<') {
                inTag = true;
                builder.append(c);
                continue;
            }

            if (c == '>') {
                inTag = false;
                builder.append(c);
                continue;
            }

            if (inTag) {
                builder.append(c);
            } else {

                int index = plain.indexOf(Character.toLowerCase(c));
                if (index != -1) {
                    builder.append(caps.charAt(index));
                } else {
                    builder.append(c);
                }
            }
        }

        return builder.toString();
    }

    public static String zebraText(String color1, String color2, String text) {

        StringBuilder builder = new StringBuilder();

        int colorIndex = 0;

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                builder.append(c);
                continue;
            }

            String activeColor = (colorIndex % 2 == 0) ? color1 : color2;
            builder.append("<").append(activeColor).append(">").append(c);

            colorIndex++;
        }

        return builder.toString();

    }
    public static String formatNumber(double number) {

        DecimalFormatSymbols symbol = new DecimalFormatSymbols(Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###", symbol);

        return formatter.format(number);
    }
}