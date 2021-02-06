package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EGameTime;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

public class SkipNightCmd extends BaseCommand {
    public SkipNightCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        MinecraftServer serv=player.getServer();
        int playerAmount=serv.getCurrentPlayerCount();
        if(playerAmount<=8){
            if(PlayerUtils.minusXPLvl(player,8))
            {
                WorldUtils.setWorldTime(player.getServerWorld(), EGameTime.DAY);
                sendMessage(player,"跳过黑夜OK");
            }else
                sendMessage(player,"经验不足，本操作需要8级经验");
        }

        return Command.SINGLE_SUCCESS;
    }
}
