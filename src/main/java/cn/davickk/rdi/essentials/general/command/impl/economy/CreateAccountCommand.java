package cn.davickk.rdi.essentials.general.command.impl.economy;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EBankResult;
import cn.davickk.rdi.essentials.general.util.BankUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CreateAccountCommand extends BaseCommand {

    public CreateAccountCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) ->
                execute(context.getSource())).
                then(Commands.argument("No", StringArgumentType.string())
                        .executes((context)
                                -> execute(context.getSource())));
    }

    private int execute(CommandSource player) throws CommandSyntaxException {
        int result = BankUtils.createAccount(player.asPlayer());

        if (result == EBankResult.ALREADY_HAVE_ACCOUNT.get()) {
            sendMessage(player.asPlayer(), "您已经有账户了");
        } else if (result == EBankResult.OK.get()) {
            sendMessage(player.asPlayer(), "成功创建账户");
        } else sendMessage(player.asPlayer(), "创建失败，请咨询腐竹");
        return Command.SINGLE_SUCCESS;
    }
}
