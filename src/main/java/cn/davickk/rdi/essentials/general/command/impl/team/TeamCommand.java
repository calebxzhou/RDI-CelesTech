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
        //sendMessage(player, "������ҵ�����");
        TextUtils.clickableContent2Send(player,"------RDI Coop 1.0------","",
                "");

        PlayerUtils.sendLoading(player);
        //ifû���Ŷ�
        TextUtils.getClickableContentComp("[�����Ŷ�]","/cp join","����һ�����е��Ŷӣ����辭�飩");
        TextUtils.getClickableContentComp("[�����Ŷ�]","/cp create","����һ���µ��Ŷӣ���Ҫ5���飩");
        //if��Ҵ������Ŷ�
        TextUtils.getClickableContentComp("[����]","/cp invite","�������˼��������Ŷ� %s����Ҫ1���飩");
        TextUtils.getClickableContentComp("[�Ƴ�]","/cp remove","��һ���˴������Ŷ����Ƴ�");
        TextUtils.getClickableContentComp("[�����ȼ�]","/cp levelup","�����Ŷӵĵȼ������ɸ�����");

        //if��Ҽ����˱��˵��Ŷ�
        TextUtils.getClickableContentComp("[�˳��Ŷ�]","/cp quit","�˳��Ŷ�%s");

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
