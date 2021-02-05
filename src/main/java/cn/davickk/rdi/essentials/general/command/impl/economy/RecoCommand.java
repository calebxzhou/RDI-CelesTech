package cn.davickk.rdi.essentials.general.command.impl.economy;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class RecoCommand extends BaseCommand {

    public RecoCommand(String name, int permissionLevel, boolean enabled) {
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

    private int execute(CommandSource source) {
        String msgHelp[] = {
                "---RDI Economy (/reco)---v1.1",
                "/money 账户余额 /pay 向他人转账 ",
                "/kaihu 创建账户 ",
                //"/rinv create 创建自己的云仓库",
                //"/rinv list 查看云仓库中的物品",
                //"/rinv push 将手上拿着的物品存入云仓库",
                //"/rinv get <格编号> 将对应编号的物品取出到背包"
        };
        try {
            ServerPlayerEntity player = source.asPlayer();
            for (int i = 0; i < msgHelp.length; i++) {
                sendMessage(player, new StringTextComponent(msgHelp[i]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Command.SINGLE_SUCCESS;
    }
}
