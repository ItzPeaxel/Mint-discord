package dev.peaxel.mintdiscord.events;

import dev.peaxel.mintdiscord.Mint;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadyEvents extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Mint.getLogger().info("Mint started! Guild connected : {}", Mint.getInstance().getGuilds().get(0).getName());
    }
}
