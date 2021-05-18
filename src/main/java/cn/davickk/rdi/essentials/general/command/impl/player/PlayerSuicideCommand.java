package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

import static cn.davickk.rdi.essentials.general.util.PlayerUtils.doSuicide;

public class PlayerSuicideCommand extends BaseCommand {

    public PlayerSuicideCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) ->
                execute(context.getSource())).
                then(Commands.argument("No", StringArgumentType.string())
                        .executes((context)
                                -> execute(context.getSource())));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();/*
        EssentialPlayer eslPlayer = DataManager.getPlayer(player);

        long cooldown = eslPlayer.getUsage().getCommandCooldown("suicide", ModConfig.suicide_player_cooldown);
        if (cooldown != 0) {
            sendMessage(player, "rdi-essentials.cooldown", cooldown);
            return Command.SINGLE_SUCCESS;
        }

        eslPlayer.getUsage().setCommandUsage("suicide");
        eslPlayer.saveData();*/

        doSuicide(player);
        MinecraftServer mcs = source.getServer();
        mcs.getCommands().performCommand(mcs.createCommandSourceStack(), "mek radiation removeAll");
        TextUtils.sendGlobalChatMessage(mcs.getPlayerList(), player.getDisplayName().getString() + " ´óº°Ò»Éù £ººßºß °¡  ºß°¡°¡°¡°¡°¡°¡°¡°¡°¡");
        return Command.SINGLE_SUCCESS;
    }

}
