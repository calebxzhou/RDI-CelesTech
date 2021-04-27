/*
package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.model.IslandLocation;
import cn.davickk.rdi.essentials.general.model.Location;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

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
        if(WorldUtils.ifNearbySpawn(loca)){
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
        }else TextUtils.sendChatMessage(player,"经验不足...")

        return Command.SINGLE_SUCCESS;
    }

}
*/
