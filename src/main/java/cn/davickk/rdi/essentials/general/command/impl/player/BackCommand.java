package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.thread.player.BackThread;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackCommand extends BaseCommand {

    public BackCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(new BackThread(source.asPlayer(), EBack.READ));
        /*ServerPlayerEntity player = source.asPlayer();
        EssentialPlayer eslPlayer = DataManager.getPlayer(player);

        Location location = eslPlayer.getData().getLastLocation();
        if (location == null) {
            sendMessage(player, "Î´ÕÒµ½Î»ÖÃ");
            return Command.SINGLE_SUCCESS;
        }

        long deathTime = eslPlayer.getData().getLastDeath();
        boolean recentlyDied = deathTime + ModConfig.back_death_custom_cooldown > currentTimestamp();

        if (ModConfig.back_death_custom_cooldown != 0 && recentlyDied) {
            long cooldown = eslPlayer.getUsage().getTeleportCooldown("back", ModConfig.back_death_custom_cooldown);
            if (cooldown != 0) {
                sendMessage(player, "back.rdi-essentials.cooldown.teleport", cooldown);
                return Command.SINGLE_SUCCESS;
            }
        } else {
            long cooldown = eslPlayer.getUsage().getTeleportCooldown("back", ModConfig.back_cooldown);
            if (cooldown != 0) {
                TextUtils.sendChatMessage(player, EnumLang.TELEPORT_COOLDOWN.translateColored(EnumColor.DARK_RED, EnumLang.GENERIC.translateColored(EnumColor.RED, cooldown)));
                return Command.SINGLE_SUCCESS;
            }
        }

        eslPlayer.getUsage().setCommandUsage("back");
        eslPlayer.saveData();

        if (simpleTeleport(player, location, "back", ModConfig.back_delay)) {
            if (ModConfig.back_delay == 0) {
                sendMessage(player, "back.rdi-essentials.success");
            } else {
                sendMessage(player, "back.rdi-essentials.success.wait", ModConfig.back_delay);
            }
        }*/

        return Command.SINGLE_SUCCESS;
    }

}
