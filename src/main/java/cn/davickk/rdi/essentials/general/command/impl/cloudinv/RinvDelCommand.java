package cn.davickk.rdi.essentials.general.command.impl.cloudinv;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EGeneral;
import cn.davickk.rdi.essentials.general.thread.rinv.RinvDelThread;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RinvDelCommand extends BaseCommand {
    public RinvDelCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) ->
                execute(context.getSource())).
                then(Commands.argument("No", StringArgumentType.string())
                        .executes((context)
                                -> execute(context.getSource())));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        //TODO UP_COMMAND
        ServerPlayerEntity player = source.asPlayer();
        sendMessage(player, EGeneral.LOADING.text);
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(new RinvDelThread(player));


        return Command.SINGLE_SUCCESS;
    }
}
