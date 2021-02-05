package cn.davickk.rdi.essentials.general.command.impl.home;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.home.ShareHomeT;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ShareHomeCmd extends BaseCommand {

    public ShareHomeCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes(
                (context) -> execute(context.getSource()))
                .then(Commands.argument("homeName", StringArgumentType.string())
                        .then(Commands.argument("player2Share",StringArgumentType.string())
                        //.suggests(ModUtils.HOME_SUGGEST)
                        .executes((context) -> execute(context.getSource(),
                                                StringArgumentType.getString(context,"homeName"),
                                                StringArgumentType.getString(context, "player2Share") ))
                        ));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        TextUtils.sendChatMessage(source.asPlayer(),"用法：/sharehome island <玩家名>");
        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source,String homeName,String player2Share) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        ServerUtils.startThread(new ShareHomeT(player,homeName,player2Share));
        return Command.SINGLE_SUCCESS;
    }
}
