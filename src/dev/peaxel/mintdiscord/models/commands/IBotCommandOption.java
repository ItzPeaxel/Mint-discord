package dev.peaxel.mintdiscord.models.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public interface IBotCommandOption {
    String name();
    String description();
    OptionType type();

    boolean isRequired();
}
