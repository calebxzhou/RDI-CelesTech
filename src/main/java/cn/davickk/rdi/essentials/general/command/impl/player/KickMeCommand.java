package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

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
        ServerPlayerEntity player = source.asPlayer();
        MinecraftServer server=player.getServer();
        server.getCommandManager().handleCommand(server.getCommandSource(),"kick "+player.getDisplayName().getString()+"�����˳�������\n���½���������󷽿ɻָ�����");
        /*Location ploc = new Location(player);
       // if(player.getServerWorld().getWorldInfo())

        double x=ploc.x;
        double y=ploc.y;
        double z=ploc.z;
        TextComponent text=new StringTextComponent
                ("������������磬��Ӧ����"+Math.round(x/8.0)
                        +","+Math.round(y)+","+Math.round(z/8.0));
        TextComponent text2=new StringTextComponent
                ("������ڵ�������Ӧ������"+Math.round(x*8.0)
                        +","+Math.round(y)+","+Math.round(z*8.0));
        sendMessage(player,text);
        sendMessage(player,text2);*/
        return Command.SINGLE_SUCCESS;
    }

}
