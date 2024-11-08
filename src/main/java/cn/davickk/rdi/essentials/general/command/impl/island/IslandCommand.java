package cn.davickk.rdi.essentials.general.command.impl.island;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.thread.island.IslandMenuT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

public class IslandCommand extends BaseCommand {
    public IslandCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.getPlayerOrException();
        sendMessage(player, EColor.ORANGE.code+"--RDI System "+ RDIEssentials.VERSION+"--");
        ServerUtils.startThread(new IslandMenuT(player));
        //----RDI Aerotech新技术空岛系统 版本v1.0---
        //[创建岛] [回岛]
        return Command.SINGLE_SUCCESS;
    }
}
