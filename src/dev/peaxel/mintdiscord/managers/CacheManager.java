package dev.peaxel.mintdiscord.managers;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;

public class CacheManager {

    private static final ArrayList<Message> messageCache = new ArrayList<>();
    private static final ArrayList<Member> memberCache = new ArrayList<>();

    public static ArrayList<Message> getMessageCache() {
        return messageCache;
    }

    public static ArrayList<Member> getMemberCache() {
        return memberCache;
    }
}
