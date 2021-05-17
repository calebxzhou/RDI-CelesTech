package cn.davickk.rdi.essentials.general.command.impl.blockrec;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.rinv.RinvThread;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class BlockRecordCmd extends BaseCommand {
    private List<String> oprationList=new ArrayList();
    private final SuggestionProvider<CommandSource> SUGGESTIONS_PROVIDER
            = (context, builder) -> ISuggestionProvider.suggest
            (oprationList, builder);
    public BlockRecordCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
        oprationList.add("inspect");
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()))
                .then(Commands.argument("opration", StringArgumentType.string())
                        .suggests(SUGGESTIONS_PROVIDER)
                        .executes((context) ->
                                execute(context.getSource(), StringArgumentType.getString(context, "opration"))
                        )
                );
    }

    private int execute(CommandSource source) {
        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source, String opration) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        if(!oprationList.contains(opration)){
            sendMessage(player,"无法识别本指令，请检查是否有输入错误。");
            return Command.SINGLE_SUCCESS;
        }
        if(opration.equals("put")){
            ServerUtils.startThread(new RinvThread(player, RinvThread.PUSH));
        }else if(opration.equals("list")){
            ServerUtils.startThread(new RinvThread(player, RinvThread.LIST));
        }else if(opration.equals("get")){
            ServerUtils.startThread(new RinvThread(player, RinvThread.GET));
        }
        return Command.SINGLE_SUCCESS;
    }
}
