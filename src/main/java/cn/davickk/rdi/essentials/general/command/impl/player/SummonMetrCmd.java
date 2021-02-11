package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.thread.player.BackThread;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sk89q.worldedit.WorldEditException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SummonMetrCmd extends BaseCommand {

    public SummonMetrCmd(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        Location loca=new Location(player);
        double diff1=Math.abs(loca.x-ServerUtils.SPAWN_LOCA.x);
        double diff2=Math.abs(loca.z-ServerUtils.SPAWN_LOCA.z);
        if(loca.x<20 && loca.z<20){
            TextUtils.sendChatMessage(player,"您不可以在主城召唤陨石。");
            return Command.SINGLE_SUCCESS;
        }
        if(PlayerUtils.minusXPLvl(player,30))
        {
            try {
                WorldUtils.pasteSchematic(player,new IslandLocation(player),"ae2yunshi");
                TextUtils.sendChatMessage(player,"召唤陨石成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else TextUtils.sendChatMessage(player,"经验不足...");
        /*ServerPlayerEntity player = source.asPlayer();
        EssentialPlayer eslPlayer = DataManager.getPlayer(player);

        Location location = eslPlayer.getData().getLastLocation();
        if (location == null) {
            sendMessage(player, "未找到位置");
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
