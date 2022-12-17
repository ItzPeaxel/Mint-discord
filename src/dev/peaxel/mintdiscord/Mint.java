package dev.peaxel.mintdiscord;

import com.github.shyiko.dotenv.DotEnv;
import dev.peaxel.mintdiscord.events.ReadyEvents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.managers.GuildManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.Map;

public class Mint {

    private static JDA instance;
    private static Map dotEnvMap;
    private static final Logger logger = LoggerFactory.getLogger("Mint");

    private static void buildJDA() throws InterruptedException {
        loadDotEnv();

        JDABuilder b = JDABuilder.create(dotEnvMap.get("token").toString(), EnumSet.allOf(GatewayIntent.class))
                .enableCache(EnumSet.allOf(CacheFlag.class))
                .setActivity(Activity.watching("t.tv/asaniiart"))
                .setStatus(OnlineStatus.ONLINE);

        addListeners(b);

        instance = b.build();
        instance.awaitReady();
    }

    private static void addListeners(JDABuilder builder){
        builder.addEventListeners(new ReadyEvents());
    }

    private static void loadDotEnv(){
        dotEnvMap = DotEnv.load();
    }

    public static void main(String[] args) {
        try {
            buildJDA();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static JDA getInstance() {
        return instance;
    }

    public static GuildManager getGuildManager(){
        return instance.getGuilds().get(0).getManager();
    }
}