package cn.davickk.rdi.essentials.general.command.impl.spawn;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.storage.IWorldInfo;

public class SpawnCommand extends BaseCommand {

    public SpawnCommand(String command, int permissionLevel, boolean enabled) {
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
        ServerPlayerEntity player = source.asPlayer();
        IWorldInfo worldInfo = player.getServerWorld().getWorldInfo();
        int x = worldInfo.getSpawnX();
        int y = worldInfo.getSpawnY();
        int z = worldInfo.getSpawnZ();
        TextUtils.sendChatMessage(player, "传送回当前世界的出生点了。");
        player.teleport(player.getServerWorld(), x, y, z, 0, 0);
        /*EssentialPlayer eslPlayer = DataManager.getPlayer(player);

        long cooldown = eslPlayer.getUsage().getTeleportCooldown("spawn", ModConfig.spawn_cooldown);
        if (cooldown != 0) {
            TextUtils.sendChatMessage(player, EnumLang.TELEPORT_COOLDOWN.translateColored(EnumColor.DARK_RED, EnumLang.GENERIC.translateColored(EnumColor.RED, cooldown)));
            return Command.SINGLE_SUCCESS;
        }

        eslPlayer.getUsage().setCommandUsage("spawn");
        eslPlayer.saveData();

        Location location = DataManager.getWorld().getSpawn();
        if (simpleTeleport(player, location, "spawn", ModConfig.spawn_delay)) {
            if (ModConfig.spawn_delay == 0) {
                TextUtils.sendChatMessage(player, EnumLang.TELEPORT_DONE.translateColored(EnumColor.DARK_GREEN));
            } else {
                sendMessage(player, "spawn.rdi-essentials.success.wait", ModConfig.spawn_delay);
            }
        }*/

        return Command.SINGLE_SUCCESS;
    }

}
