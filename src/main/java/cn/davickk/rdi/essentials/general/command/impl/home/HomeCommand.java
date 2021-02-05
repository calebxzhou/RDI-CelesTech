package cn.davickk.rdi.essentials.general.command.impl.home;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EGeneral;
import cn.davickk.rdi.essentials.general.thread.home.HomeListThread;
import cn.davickk.rdi.essentials.general.thread.home.HomeThread;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
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

public class HomeCommand extends BaseCommand {

    public HomeCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes(
                (context) -> execute(context.getSource()))
                .then(Commands.argument("homeName", StringArgumentType.string())
                        //.suggests(ModUtils.HOME_SUGGEST)
                        .executes(
                                (context) ->
                                        execute(context.getSource(),
                                                StringArgumentType.getString(context, "homeName"))
                        )
                );
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        //sendMessage(player, "������ҵ�����");
        TextUtils.clickableContent2Send(player,"------RDI Homeϵͳ �汾v2.12------","",
                "��лKenRoKoro,pop75189,SampsonnZ\nFunnyTea233���ڱ�ϵͳ�����Ľ���͹��ף����������Ⱥ�");
        PlayerUtils.sendLoading(player);
        ServerUtils.startThread(new HomeListThread(player));
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, String homeName) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        PlayerUtils.sendLoading(player);
        ServerUtils.startThread(new HomeThread(player, homeName));
        return Command.SINGLE_SUCCESS;
    }

}
