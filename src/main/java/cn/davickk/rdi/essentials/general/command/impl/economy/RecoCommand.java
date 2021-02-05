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
                "/money �˻���� /pay ������ת�� ",
                "/kaihu �����˻� ",
                //"/rinv create �����Լ����Ʋֿ�",
                //"/rinv list �鿴�Ʋֿ��е���Ʒ",
                //"/rinv push ���������ŵ���Ʒ�����Ʋֿ�",
                //"/rinv get <����> ����Ӧ��ŵ���Ʒȡ��������"
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
