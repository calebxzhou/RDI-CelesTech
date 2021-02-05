package cn.davickk.rdi.essentials.general.command.impl.rdi;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.command.impl.player.BackCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.thread.island.IslandMenuT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

public class RdiCommand extends BaseCommand {
    public RdiCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }
    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        sendMessage(player, EColor.AQUA+"--RDI CelesTech Skyblock �¼����յ�ϵͳV1.0--");
        ServerUtils.startThread(new IslandMenuT(player));
        //----RDI Aerotech�¼����յ�ϵͳ �汾v1.0---
        //[������] [�ص�]
        return Command.SINGLE_SUCCESS;
    }
}
