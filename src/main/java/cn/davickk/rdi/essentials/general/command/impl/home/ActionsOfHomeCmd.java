package cn.davickk.rdi.essentials.general.command.impl.home;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.home.ActionsOfHomeT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ActionsOfHomeCmd extends BaseCommand {
    public ActionsOfHomeCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes(
                (context) -> execute(context.getSource()))
                .then(Commands.argument("homeName", StringArgumentType.string())
                                .executes((context) -> execute(context.getSource(),
                                        StringArgumentType.getString(context,"homeName"))));
    }



    private int execute(CommandSource source) throws CommandSyntaxException {
        sendMessage(source.asPlayer(),"请指定家的名称，以查看具体操作");
        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source, String homeName) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        ServerUtils.startThread(new ActionsOfHomeT(source.asPlayer(),homeName));
        return Command.SINGLE_SUCCESS;
    }
}
