package cn.davickk.rdi.essentials.general.command.impl.server;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.server.SQLReconnT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import org.apache.logging.log4j.core.jmx.Server;

import java.sql.SQLException;

public class ReconnSQLCmd extends BaseCommand {

    public ReconnSQLCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) {
        ServerUtils.startThread(new SQLReconnT());

        return Command.SINGLE_SUCCESS;
    }
}
