package cn.davickk.rdi.essentials.general.command.impl.cloudinv;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EGeneral;
import cn.davickk.rdi.essentials.general.thread.rinv.RinvDownThread;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RinvDownCommand extends BaseCommand {

    public RinvDownCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) ->
                execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        /*TextUtils.sendTitle(player.getServer().getCommandSource(), player,TextUtils.TITLE , EColor.RED.code
                + EColor.BOLD.code + "请不要与背包交互");
        TextUtils.sendTitle(player.getServer().getCommandSource(), player,TextUtils.SUBTITLE,
                "否则会导致数据丢失");*/
        sendMessage(player, EGeneral.LOADING.text);
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(new RinvDownThread(player));

        return Command.SINGLE_SUCCESS;
    }
}