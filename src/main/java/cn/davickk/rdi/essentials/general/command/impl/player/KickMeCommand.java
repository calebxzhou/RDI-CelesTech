package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

public class KickMeCommand extends BaseCommand {
    public KickMeCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context)
                -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        MinecraftServer server=player.getServer();
        server.getCommands().performCommand(server.createCommandSourceStack(),"kick "+player.getDisplayName().getString()+"您已退出服务器\n重新进入服务器后方可恢复正常");
        /*Location ploc = new Location(player);
       // if(player.getCommandSenderWorld().getWorldInfo())

        double x=ploc.x;
        double y=ploc.y;
        double z=ploc.z;
        TextComponent text=new StringTextComponent
                ("如果你在主世界，对应地狱"+Math.round(x/8.0)
                        +","+Math.round(y)+","+Math.round(z/8.0));
        TextComponent text2=new StringTextComponent
                ("如果你在地狱，对应主世界"+Math.round(x*8.0)
                        +","+Math.round(y)+","+Math.round(z*8.0));
        sendMessage(player,text);
        sendMessage(player,text2);*/
        return Command.SINGLE_SUCCESS;
    }

}
