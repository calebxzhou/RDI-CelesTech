package cn.davickk.rdi.essentials.general.command.impl.island;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.island.CreateIslandHereT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;

public class CreateIslandHereCmd extends BaseCommand {
    public CreateIslandHereCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        //----RDI Aerotech新技术空岛系统 版本v1.0---
        //[创建岛] [回岛]
        ServerUtils.startThread(new CreateIslandHereT(source.getPlayerOrException()));
        return Command.SINGLE_SUCCESS;
    }
}
