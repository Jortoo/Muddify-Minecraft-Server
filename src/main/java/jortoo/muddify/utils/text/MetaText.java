package jortoo.muddify.utils.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MetaText {

    private String text;

    public MetaText(String text) {
        this.text = text;
    }

    public Component deser() { return MiniMessage.miniMessage().deserialize( "<i:false>" + this.text); }

}
