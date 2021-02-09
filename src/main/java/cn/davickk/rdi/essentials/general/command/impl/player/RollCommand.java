package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.thread.player.BackThread;
import cn.davickk.rdi.essentials.general.thread.player.TechRollT;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RollCommand extends BaseCommand {

    public RollCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        if(PlayerUtils.minusXPLvl(source.asPlayer(), 5))
        ServerUtils.startThread(new TechRollT(source.asPlayer()));
        else
            TextUtils.sendChatMessage(source.asPlayer(), "¾­Ñé²»×ã");


        return Command.SINGLE_SUCCESS;
    }

}
