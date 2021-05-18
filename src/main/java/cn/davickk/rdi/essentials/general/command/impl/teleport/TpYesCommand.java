package cn.davickk.rdi.essentials.general.command.impl.teleport;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.teleport.AcceptTpaRequestT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class TpYesCommand extends BaseCommand {

    public TpYesCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()))
                .then(Commands.argument("reqid", StringArgumentType.string())
                        .executes(context -> execute(context.getSource(), StringArgumentType.getString(context, "reqid"))));
    }


    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        TextUtils.sendChatMessage(player,"本功能需要RDI Request ID，如果不知道这是什么，请直接点“接受”按钮，不要使用本指令");
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, String reqid) throws CommandSyntaxException {
        ServerPlayerEntity toPlayer = source.getPlayerOrException();
        ServerUtils.startThread(new AcceptTpaRequestT(toPlayer,reqid));
        return Command.SINGLE_SUCCESS;
    }
}
