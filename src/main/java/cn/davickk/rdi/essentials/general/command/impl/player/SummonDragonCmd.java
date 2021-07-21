
package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.Debugger;
import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.model.IslandLocation;
import cn.davickk.rdi.essentials.general.model.Location;
import cn.davickk.rdi.essentials.general.thread.server.RestartTask;
import cn.davickk.rdi.essentials.general.thread.server.SummonDragonTask;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Dimension;

import java.util.Timer;

public class SummonDragonCmd extends BaseCommand {

    public SummonDragonCmd(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.getPlayerOrException();
        ResourceLocation r1=player.getCommandSenderWorld().dimension().location();
        ResourceLocation r2=Dimension.END.location();
        //Debugger.debugPrint(r1+","+r2);
        if(r1!=r2){
            sendMessage(player,"您只能在末地使用本指令。");
            return Command.SINGLE_SUCCESS;
        }
        if(PlayerUtils.minusXPLvl(player,45))
        {
            Vector3d playerPos=player.position();
            playerPos.add(0,50,0);
            sendMessage(player,"10秒后即将在您的上方50格刷出末影龙，不要退出游戏。");
            new Timer().schedule(new SummonDragonTask(player.getCommandSenderWorld(),
                    playerPos),10000L);
        }else {
            TextUtils.sendChatMessage(player,"经验不足...");
        }
        return Command.SINGLE_SUCCESS;
    }

}
