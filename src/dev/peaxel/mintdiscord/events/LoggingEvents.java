package dev.peaxel.mintdiscord.events;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingEvents extends ListenerAdapter {

    private final Logger logger = LoggerFactory.getLogger("Mint-Logger");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)){
            logger.info("<{}> {}", event.getAuthor().getAsTag(), event.getMessage().getContentRaw());
        } else {
            logger.info("(#{}) <{}> {}", event.getChannel().getName(), event.getAuthor().getAsTag(), event.getMessage().getContentRaw());
        }
    }
}
