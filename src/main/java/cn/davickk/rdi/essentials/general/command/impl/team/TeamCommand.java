package cn.davickk.rdi.essentials.general.command.impl.team;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
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
        return builder.executes(
                (context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        //sendMessage(player, "������ҵ�����");
        TextUtils.clickableContent2Send(player,"------RDI Team ���ϵͳ �汾v1.0------","",
                "");

        PlayerUtils.sendLoading(player);
        //ifû���Ŷ�
        TextUtils.getClickableContentComp(player,"[�����Ŷ�]","/team join","����һ�����е��Ŷӣ����辭�飩");
        TextUtils.getClickableContentComp(player,"[�����Ŷ�]","/team create","����һ���µ��Ŷӣ���Ҫ5���飩");
        //if��Ҵ������Ŷ�
        TextUtils.getClickableContentComp(player,"[����]","/team invite","�������˼��������Ŷ� %s����Ҫ1���飩");
        TextUtils.getClickableContentComp(player,"[�Ƴ�]","/team remove","��һ���˴������Ŷ����Ƴ�");
        TextUtils.getClickableContentComp(player,"[�����ȼ�]","/team levelup","�����Ŷӵĵȼ������ɸ�����");

        //if��Ҽ����˱��˵��Ŷ�
        TextUtils.getClickableContentComp(player,"[�˳��Ŷ�]","/team quit","�˳��Ŷ�%s");

        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, String homeName) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        PlayerUtils.sendLoading(player);
        return Command.SINGLE_SUCCESS;
    }

}
