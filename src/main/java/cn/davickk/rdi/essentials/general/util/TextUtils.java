package cn.davickk.rdi.essentials.general.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.impl.TellRawCommand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class TextUtils {
    public static final String TITLE="title";
    public static final String SUBTITLE="subtitle";
    private static void sendMessage(ServerPlayerEntity player, IFormattableTextComponent textComponent, boolean actionBar) {
        player.sendStatusMessage(textComponent, actionBar);
    }

    private static void sendMessage(PlayerEntity player, IFormattableTextComponent textComponent, boolean actionBar) {
        player.sendStatusMessage(textComponent, actionBar);
    }

    private static void sendGlobalMessage(PlayerList players, IFormattableTextComponent textComponent, boolean actionBar) {
        for (int i = 0; i < players.getPlayers().size(); ++i) {
            ServerPlayerEntity player = players.getPlayers().get(i);
            sendMessage(player, textComponent, actionBar);
        }
    }

    //source一般是服务器
    public static void sendTitle(CommandSource source, ServerPlayerEntity playerToSend, String type, String content){
        MinecraftServer mcs=source.getServer();
        mcs.getCommandManager().handleCommand(source,"title "
                +playerToSend.getDisplayName().getString()+" "+type+
                        " {\"text\":\""+content+"\"}");
        /* Class cls = Class.forName("net.minecraft.command.impl.TitleCommand");
        Method titlefunc = cls.getDeclaredMethod("show",
                CommandSource.class, Collection.class, ITextComponent.class, STitlePacket.Type.class);
        titlefunc.setAccessible(true);
        //int show(CommandSource source,
        // Collection<ServerPlayerEntity> targets,
        // ITextComponent message, STitlePacket.Type type)
        ArrayList<ServerPlayerEntity> targets = new ArrayList<>();
        targets.add(playerToSend);
        titlefunc.invoke(cls, source,
                targets, new StringTextComponent(content), type);*/
    }

    public static void sendMsgBySrc(CommandSource source, String content) {
        source.sendFeedback(new StringTextComponent(content), true);
    }

    // Chat msg
    public static void sendChatMessage(ServerPlayerEntity player, IFormattableTextComponent textComponent) {
        sendMessage(player, textComponent, false);
    }

    public static void sendChatMessage(ServerPlayerEntity player, String content) {
        sendMessage(player, new StringTextComponent(content), false);
    }

    public static void sendChatMessage(PlayerEntity player, String content) {
        sendMessage(player, new StringTextComponent(content), false);
    }

    public static void sendChatMessage(ServerPlayerEntity player, String translationKey, Object... args) {
        sendMessage(player, new TranslationTextComponent(translationKey, args), false);
    }

    // Action Bar

    public static void sendActionMessage(ServerPlayerEntity player, IFormattableTextComponent textComponent) {
        sendMessage(player, textComponent, true);
    }

    public static void sendActionMessage(ServerPlayerEntity player, String content) {
        sendMessage(player, new StringTextComponent(content), true);
    }

    public static void sendActionMessage(ServerPlayerEntity player, String translationKey, Object... args) {
        sendMessage(player, new TranslationTextComponent(translationKey, args), true);
    }

    // Global msg

    public static void sendGlobalActionMessage(PlayerList players, String translationKey, Object... args) {
        sendGlobalMessage(players, new TranslationTextComponent(translationKey, args), true);
    }

    public static void sendGlobalActionMessage(PlayerList players, IFormattableTextComponent textComponent) {
        sendGlobalMessage(players, textComponent, true);
    }

    // Global Action Bar

    public static void sendGlobalChatMessage(PlayerList players, String translationKey, Object... args) {
        sendGlobalMessage(players, new TranslationTextComponent(translationKey, args), false);
    }

    public static void sendGlobalChatMessage(PlayerList players, TextComponent textComponent) {
        sendGlobalMessage(players, textComponent, false);
    }

    public static void sendGlobalChatMessage(PlayerList players, String content) {
        sendGlobalMessage(players, new StringTextComponent(content), false);
    }

    //TELLRAW
    public static void clickableContent2Send(ServerPlayerEntity player, String content, String commandTodo)
    {
        TextComponent comp=new StringTextComponent(content);
        comp.setStyle(comp.getStyle()
            .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,commandTodo))
            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new StringTextComponent("点击以执行"))));

        player.sendMessage(comp, UUID.randomUUID());
        //TextUtils.sendChatMessage(player,comp.getStyle().toString());
        /*
        MinecraftServer server=player.getServer();
        String name=player.getDisplayName().getString();
        String cmd="tellraw "+name+" {\"text\":\""+content+"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/"+commandTodo+"\"}}";
        System.out.println(cmd);
        server.getCommandManager().handleCommand(server.getCommandSource(),cmd);*/
    }
    public static void clickableContent2Send(PlayerEntity player, String content, String commandTodo, String hoverContent)
    {
        TextComponent comp=new StringTextComponent(content);
        comp.setStyle(comp.getStyle()
                .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,commandTodo))
                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new StringTextComponent(hoverContent))));

        player.sendMessage(comp, UUID.randomUUID());
        //TextUtils.sendChatMessage(player,comp.getStyle().toString());
        /*
        MinecraftServer server=player.getServer();
        String name=player.getDisplayName().getString();
        String cmd="tellraw "+name+" {\"text\":\""+content+"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/"+commandTodo+"\"}}";
        System.out.println(cmd);
        server.getCommandManager().handleCommand(server.getCommandSource(),cmd);*/
    }
    public static IFormattableTextComponent getClickableContentComp(PlayerEntity player, String content, String commandTodo, String hoverContent)
    {
        TextComponent comp=new StringTextComponent(content);
        return comp.setStyle(comp.getStyle()
                .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,commandTodo))
                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new StringTextComponent(hoverContent))));
    }
    public static IFormattableTextComponent appendTwoComp(IFormattableTextComponent c1,IFormattableTextComponent c2)
    {
        return c1.append(c2);
    }
    public static IFormattableTextComponent appendTwoComp(String c1,IFormattableTextComponent c2)
    {
        return new StringTextComponent(c1).append(c2);
    }
    public static IFormattableTextComponent appendTwoComp(IFormattableTextComponent c1,String c2)
    {
        return c1.append(new StringTextComponent(c2));
    }

}
