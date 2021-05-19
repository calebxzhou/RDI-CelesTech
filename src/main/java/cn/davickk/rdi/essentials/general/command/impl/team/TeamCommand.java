package cn.davickk.rdi.essentials.general.command.impl.team;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class TeamCommand extends BaseCommand {

    public TeamCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()))
                .then(Commands.argument("opr", StringArgumentType.string())
                        .executes(context -> execute(context.getSource(), StringArgumentType.getString(context, "opr"))));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        //sendMessage(player, "请输入家的名称");
        TextUtils.clickableContent2Send(player,"------RDI Coop 1.0------","",
                "");

        PlayerUtils.sendLoading(player);
        //if没有团队
        TextUtils.getClickableContentComp("[加入团队]","/cp join","加入一个已有的团队（无需经验）");
        TextUtils.getClickableContentComp("[创建团队]","/cp create","创建一个新的团队（需要5经验）");
        //if玩家创建了团队
        TextUtils.getClickableContentComp("[邀请]","/cp invite","邀请他人加入您的团队 %s（需要1经验）");
        TextUtils.getClickableContentComp("[移除]","/cp remove","将一个人从您的团队中移除");
        TextUtils.getClickableContentComp("[提升等级]","/cp levelup","提升团队的等级以容纳更多人");

        //if玩家加入了别人的团队
        TextUtils.getClickableContentComp("[退出团队]","/cp quit","退出团队%s");

        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, String opr) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();

        /*if(opr.equals("join"))
            else if(opr.equals("create"))
                //TODO*/
        return Command.SINGLE_SUCCESS;
    }

}
