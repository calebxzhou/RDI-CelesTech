package cn.davickk.rdi.essentials.general.command.impl.home;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.home.RenameHomeT;
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

public class RenameHomeCmd extends BaseCommand {

    public RenameHomeCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes(
                (context) -> execute(context.getSource()))
                .then(Commands.argument("old", StringArgumentType.string())
                        .then(Commands.argument("new",StringArgumentType.string())
                        //.suggests(ModUtils.HOME_SUGGEST)
                        .executes((context) -> execute(context.getSource(),
                                                StringArgumentType.getString(context,"old"),
                                                StringArgumentType.getString(context, "new") ))
                        ));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        TextUtils.sendChatMessage(source.asPlayer(),"您想要给哪个家改名？/renamehome <旧名称> <新名称>");
        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source,String oldName,String newName) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        ServerUtils.startThread(new RenameHomeT(player,oldName,newName));
        return Command.SINGLE_SUCCESS;
    }
}
