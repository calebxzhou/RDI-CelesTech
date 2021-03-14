package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EDeathItemReq;
import cn.davickk.rdi.essentials.general.thread.death.DeathItemT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.sql.SQLException;

public class GetFromVoidCmd extends BaseCommand {
    public GetFromVoidCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource cs) throws CommandSyntaxException {
        ServerPlayerEntity player=cs.asPlayer();
        try {
            ServerUtils.startThread(new DeathItemT(player, EDeathItemReq.READ));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Command.SINGLE_SUCCESS;
    }

}
