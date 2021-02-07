package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

public class RemoveGroundCmd extends BaseCommand {
    public RemoveGroundCmd(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context)
                -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        /*if(player.experienceLevel<1){
            TextUtils.sendChatMessage(player,"���������Ҫ1���飬���ľ��鲻�㡣");
            return Command.SINGLE_SUCCESS;
        }*/
        int requireXp= (int) Math.round(player.experienceLevel*0.5);
        if(!PlayerUtils.minusXPLvl(player,requireXp)){
            TextUtils.sendChatMessage(player,"���������Ҫ"+requireXp+"���飬���ľ��鲻�㡣");
            return Command.SINGLE_SUCCESS;
        }
        WorldUtils.removeGround(player);
        TextUtils.sendChatMessage(player,"�ɹ�����˲ݵء�");
        return Command.SINGLE_SUCCESS;
    }

}
