package cn.davickk.rdi.essentials.general.command;

import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

public abstract class BaseCommand {

    protected LiteralArgumentBuilder<CommandSource> builder;
    boolean enabled;

    public BaseCommand(String name, int permissionLevel, boolean enabled) {
        this.builder = Commands.literal(name).requires(source -> source.hasPermission(permissionLevel));
        this.enabled = enabled;
    }

    public LiteralArgumentBuilder<CommandSource> getBuilder() {
        return builder;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return null;
    }

    public void sendMessage(ServerPlayerEntity player, String translationKey, Object... args) {
        TextUtils.sendChatMessage(player, translationKey, args);
    }

    public void sendMessage(ServerPlayerEntity player, TextComponent textComponent) {
        TextUtils.sendChatMessage(player, textComponent);
    }

    public void sendMessage(ServerPlayerEntity player, String content) {
        TextUtils.sendChatMessage(player, new StringTextComponent(content));
    }

    public void sendGlobalMessage(PlayerList players, String translationKey, Object... args) {
        TextUtils.sendGlobalChatMessage(players, translationKey, args);
    }

}
