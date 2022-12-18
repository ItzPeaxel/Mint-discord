package dev.peaxel.mintdiscord.events;

import dev.peaxel.mintdiscord.Mint;
import dev.peaxel.mintdiscord.managers.CacheManager;
import dev.peaxel.mintdiscord.utils.ChannelIds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class LoggingEvents extends ListenerAdapter {

    private final Logger logger = LoggerFactory.getLogger("Mint-Logger");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMember().getUser().isBot()) return;

        if (event.isFromType(ChannelType.PRIVATE)){
            logger.info("<{}> {}", event.getAuthor().getAsTag(), event.getMessage().getContentRaw());
        } else {
            CacheManager.getMessageCache().add(event.getMessage());
            logger.info("(#{}) <{}> {}", event.getChannel().getName(), event.getAuthor().getAsTag(), event.getMessage().getContentRaw());
        }
    }

    @Override
    public void onMessageDelete(MessageDeleteEvent event) {

        String message = "(message inconnu)";
        String member = "(Autheur inconnu)";

        for (Message m : CacheManager.getMessageCache()){
            if (m.getId().equalsIgnoreCase(event.getMessageId())){
                if (m.getMember().getUser().isBot()) return;

                message = m.getContentRaw();
                member = m.getMember().getUser().getAsTag();

                CacheManager.getMessageCache().remove(m);
                break;
            }
        }

        EmbedBuilder b = new EmbedBuilder()
                .setTitle("Deleted Message !")
                .setThumbnail(Mint.getInstance().getUserByTag(member).getAvatarUrl())
                .addField("Message", message, false)
                .addField("Author", member, true)
                .addField("Channel", event.getChannel().getName(), true)
                .setFooter("Message ID: "+event.getMessageId())
                .setColor(Color.RED);

        Mint.getInstance().getTextChannelById(ChannelIds.logs).sendMessageEmbeds(b.build()).queue();
    }
}
