package cn.davickk.rdi.essentials.general.command.impl.teleport;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class TpNoCmd extends BaseCommand {

    public TpNoCmd(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource())).then(Commands.argument("targetPlayer", EntityArgument.players()).executes(context -> execute(context.getSource(), EntityArgument.getPlayer(context, "targetPlayer"))));
    }


    private int execute(CommandSource source) throws CommandSyntaxException {
       /* ServerPlayerEntity player = source.asPlayer();
        ArrayList<TeleportRequest> requests = TeleportUtils.findRequest(player);
        if (requests.size() == 1) {
            TeleportUtils.declineRequest(requests.get(0));
        } else if (requests.size() > 1) {
           sendMessage(player,new StringTextComponent("有好几个获取坐标请求，你指的哪个啊~？ （233"));
            // sendMessage(player, "rdi-essentials.specify.player");
        } else {
           sendMessage(player,new StringTextComponent("最近无获取坐标请求"));
            // sendMessage(player, "tpa.rdi-essentials.no_request");
        }*/
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, ServerPlayerEntity targetPlayer) throws CommandSyntaxException {
        /*ServerPlayerEntity player = source.asPlayer();
        TeleportRequest tpR = TeleportUtils.findRequest(player, targetPlayer);
        if (tpR != null) {
            TeleportUtils.declineRequest(tpR);
        } else {
            sendMessage(player,new StringTextComponent("无此请求，id错了吗？"));
            //sendMessage(player, "tpa.rdi-essentials.not_found");
        }*/
        return Command.SINGLE_SUCCESS;
    }

}
