package cn.davickk.rdi.essentials.general.command.impl.admin;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

public class HideSelfCommand extends BaseCommand {
    public HideSelfCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity playerEntity=source.getPlayerOrException();
        playerEntity.getServer().getPlayerList().removePlayer(playerEntity);
        return 1;
    }
}
