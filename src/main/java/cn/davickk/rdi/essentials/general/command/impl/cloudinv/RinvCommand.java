package cn.davickk.rdi.essentials.general.command.impl.cloudinv;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.ERinv;
import cn.davickk.rdi.essentials.general.thread.rinv.RinvThread;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RinvCommand extends BaseCommand {
    private List<String> oprationList=new ArrayList();
    private final SuggestionProvider<CommandSource> SUGGESTIONS_PROVIDER
            = (context, builder) -> ISuggestionProvider.suggest
                    (oprationList, builder);
    private final String msgHelp[] = {
            "---RDI云仓库系统---v2.1",
            "/rinv list 查看云仓库中的物品",
            "/rinv put 将背包中所有的物品存入云仓库",
            "/rinv get  将云仓库中的物品下载到背包"
    };
    public RinvCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
        oprationList.add("put");
        oprationList.add("list");
        oprationList.add("get");
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

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        for (int i = 0; i < msgHelp.length; i++) {
            sendMessage(player, new StringTextComponent(msgHelp[i]));
        }
        IFormattableTextComponent up=TextUtils.getClickableContentComp(player, ERinv.UPLOAD.text,ERinv.UPLOAD.cmd,ERinv.UPLOAD_HOVER.text);
        IFormattableTextComponent down=TextUtils.getClickableContentComp(player, ERinv.DOWNLOAD.text,ERinv.DOWNLOAD.cmd,ERinv.DOWNLOAD_HOVER.text);
        IFormattableTextComponent list=TextUtils.getClickableContentComp(player, ERinv.LIST.text,ERinv.LIST.cmd,ERinv.LIST_HOVER.text);
        TextUtils.sendChatMessage(player,up.append(down).append(list));
        return Command.SINGLE_SUCCESS;
    }


    private int execute(CommandSource source, String opration) throws CommandSyntaxException {
        ServerPlayerEntity player=source.getPlayerOrException();
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