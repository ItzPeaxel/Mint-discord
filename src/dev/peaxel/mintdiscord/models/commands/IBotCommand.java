package dev.peaxel.mintdiscord.models.commands;

public interface IBotCommand {
    String name();
    String description();
    CommandType type();

    IBotCommandOption[] options();
}
