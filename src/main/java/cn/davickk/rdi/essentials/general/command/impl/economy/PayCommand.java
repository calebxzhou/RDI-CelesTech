package cn.davickk.rdi.essentials.general.command.impl.economy;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class PayCommand extends BaseCommand {

    public PayCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder
                .executes((context) -> execute(context.getSource()))
                .then(Commands.argument("player", StringArgumentType.string())
                        .executes((context) ->
                                execute(context.getSource(),
                                        StringArgumentType.getString(context, "player"))))
                .then(Commands.argument("money", DoubleArgumentType.doubleArg(0)))
                .executes((context) ->
                        execute(context.getSource(),
                                StringArgumentType.getString(context, "player"),
                                DoubleArgumentType.getDouble(context, "money")));

    }

    private int execute(CommandSource cs) {
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource cs, String opposite) {
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource cs, String opposite, double moneyToPay) {
        return Command.SINGLE_SUCCESS;
    }
}
