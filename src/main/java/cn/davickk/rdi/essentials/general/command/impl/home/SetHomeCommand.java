package cn.davickk.rdi.essentials.general.command.impl.home;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.home.SethomeThread;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class SetHomeCommand extends BaseCommand {

    public SetHomeCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()))
                .then(Commands.argument("homeName", StringArgumentType.string())
                        .executes((context) -> execute(context.getSource(), StringArgumentType.getString(context, "homeName"))));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();
        sendMessage(player, "请输入家的名称，例如 111, a, jia");
        //doSetHome(player, "home");
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, String homeName) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrException();

        PlayerUtils.sendLoading(player);

        ServerUtils.startThread(new SethomeThread(player, homeName));
       /* if(result== EHomeResult.OK.get())
            sendMessage(player,"成功设置了家："+homeName);
        else if(result==EHomeResult.HOME_ALREADY_EXIST.get())
            sendMessage(player,"这个家名称已经存在了，请换一个吧");
        else if(result==EHomeResult.NO_ENOUGH_$.get())
            sendMessage(player,"您没有足够的钱来设置家");
        else if(result==EHomeResult.HOME_REACHED_MAX_AMOUNT.get())
            sendMessage(player,"最多只能设置10个家");*/
        return Command.SINGLE_SUCCESS;
    }


    //EssentialPlayer eslPlayer = DataManager.getPlayer(player);
/*
        int homes_limit = 1;
        //TODO
        // ModConfig.homes_limit;
        if (player.hasPermissionLevel(1)) {
            homes_limit = ModConfig.homes_limit_op;
        }

        if ((eslPlayer.getHomeData().getHomes().size() < homes_limit)  || (eslPlayer.getHomeData().getHomes().size() == homes_limit && eslPlayer.getHomeData().getHome(name) != null)) {
            Location lc=new Location(player);
            eslPlayer.getHomeData().setHome(name, lc);
            eslPlayer.saveHomes();
            TextComponent text=new StringTextComponent("成功设置家："+name);//+" "+player.getPositionVec().toString());
            sendMessage(player,text);
            //sendMessage(player, "sethome.rdi-essentials.done", name);
        } else {
            //TextComponent text=new StringTextComponent("最多可以设置 "+homes_limit+" 个家");
            sendMessage(player,new StringTextComponent("你已经设置过"+name+"这个家了"));
            //sendMessage(player, "sethome.rdi-essentials.max_homes", homes_limit);
        }*/


}
