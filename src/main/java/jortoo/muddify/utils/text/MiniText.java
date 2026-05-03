package jortoo.muddify.utils.text;

public class MiniText {

    private String text;

    public MiniText(String text) {
        this.text = text;
    }

    public String create() {
        if (this.text == null) return "";

        String plain = "abcdefghijklmnopqrstuvwxyz";
        String caps  = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀsᴛᴜᴠᴡxʏᴢ";

        StringBuilder builder = new StringBuilder();
        boolean inTag = false;

        for (char c : this.text.toCharArray()) {

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
}