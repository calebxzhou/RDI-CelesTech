package cn.davickk.rdi.essentials.general.command.impl.home;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.home.UpdateHomeT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UpdateHomeCmd extends BaseCommand {
    private List<String> oprationList=new ArrayList();
    private final SuggestionProvider<CommandSource> SUGGESTIONS_PROVIDER
            = new SuggestionProvider<CommandSource>() {
        @Override
        public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSource> context, SuggestionsBuilder builder)
                throws CommandSyntaxException {
            return ISuggestionProvider.suggest
                    (oprationList, builder);
        }
    };
    public UpdateHomeCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
        oprationList.add("--locate");
        oprationList.add("--rename");
        oprationList.add("--comment");
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder
                .then(
                        Commands.argument("homeName",StringArgumentType.string())
                            .then(Commands.argument("opration", StringArgumentType.string()).suggests(SUGGESTIONS_PROVIDER)
                                .then(Commands.argument("argument",StringArgumentType.string())
                                        .executes(context ->
                                                UpdateHomeCmd.this.execute(
                                                        context.getSource(),
                                                        StringArgumentType.getString(context, "homeName"),
                                                        StringArgumentType.getString(context, "opration"),
                                                        StringArgumentType.getString(context, "argument")
                                                )
                                        )
                                )
                            )
                );
    }
//将 当前所在位置 设置为 空岛传送点位置 /updatehome island --locate here
    //将 传送点aaa 更名为 bbb /updatehome aaa --rename bbb
    private int execute(CommandSource source, String homeName, String opration, String argument) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        if(!this.oprationList.contains(opration)){
            TextUtils.sendChatMessage(player,"请输入正确的操作参数，详见群文件“home系列指令的使用方法”");
            return Command.SINGLE_SUCCESS;
        }
        if(opration.equals("--locate")){
            ServerUtils.startThread(new UpdateHomeT(player,homeName,UpdateHomeT.LOCATE,argument));
        }else if(opration.equals("--rename")){
            ServerUtils.startThread(new UpdateHomeT(player,homeName,UpdateHomeT.RENAME,argument));
        }else if(opration.equals("--comment")){
            ServerUtils.startThread(new UpdateHomeT(player,homeName,UpdateHomeT.COMMENT,argument));
        }
        else{
            TextUtils.sendChatMessage(player,"请输入正确的操作参数，详见群文件“home系列指令的使用方法”");

        }
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        TextUtils.sendChatMessage(source.asPlayer(),"请输入完整指令，详见群文件“home系列指令的使用方法”");
        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source,String homeName) throws CommandSyntaxException {
        TextUtils.sendChatMessage(source.asPlayer(),"请输入完整指令，详见群文件“home系列指令的使用方法”");
        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source,String homeName,String opration) throws CommandSyntaxException {
        TextUtils.sendChatMessage(source.asPlayer(),"请输入完整指令，详见群文件“home系列指令的使用方法”");
        return Command.SINGLE_SUCCESS;
    }
}
