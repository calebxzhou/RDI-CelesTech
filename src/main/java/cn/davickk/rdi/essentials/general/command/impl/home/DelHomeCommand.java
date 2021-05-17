package cn.davickk.rdi.essentials.general.command.impl.home;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.thread.home.DelhomeThread;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class DelHomeCommand extends BaseCommand {

    public DelHomeCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource())).then(Commands.argument("homeName", StringArgumentType.string()).executes((context) -> execute(context.getSource(), StringArgumentType.getString(context, "homeName"))));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        sendMessage(player, "您想删除哪个家？");
        TextUtils.clickableContent2Send(player, EColor.YELLOW.code+"[查看所有的家]","/listhome");
        //TextComponent text=new StringTextComponent("请指定家的名称");
        // sendMessage(player,text);
        //sendMessage(player, "home.rdi-essentials.specify_name");
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, String homeName) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        ServerUtils.startThread(new DelhomeThread(player,homeName));

        return Command.SINGLE_SUCCESS;
    }

}
