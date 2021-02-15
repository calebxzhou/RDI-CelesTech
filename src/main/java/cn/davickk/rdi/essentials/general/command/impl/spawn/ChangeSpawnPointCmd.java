package cn.davickk.rdi.essentials.general.command.impl.spawn;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.thread.home.HomeListThread;
import cn.davickk.rdi.essentials.general.thread.home.HomeThread;
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
import net.minecraft.util.text.IFormattableTextComponent;

public class ChangeSpawnPointCmd extends BaseCommand {

    public ChangeSpawnPointCmd(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()))
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
        IFormattableTextComponent cis=TextUtils.getClickableContentComp(player, EColor.ORANGE.code+"[�Ļص����͵�]",
                "/changesp island","���ķ��ؿյ�ʱ�Ĵ��͵�");
        IFormattableTextComponent crsp=TextUtils.getClickableContentComp(player, EColor.ORANGE.code+"[�ĸ���͵�]",
                "/changesp respawn","���ĸ���ʱ�Ĵ��͵�");
        IFormattableTextComponent cjoin=TextUtils.getClickableContentComp(player, EColor.ORANGE.code+"[�Ľ������͵�]",
                "/changesp join","���Ľ��������ʱ�Ĵ��͵�");
        TextUtils.sendChatMessage(player,cis.append(TextUtils.SPACE).append(crsp).append(TextUtils.SPACE).append(cjoin));
        PlayerUtils.sendLoading(player);
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, String arg) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        PlayerUtils.sendLoading(player);
        //ServerUtils.startThread(new HomeThread(player, homeName));
        return Command.SINGLE_SUCCESS;
    }

}
