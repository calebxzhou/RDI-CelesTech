package cn.davickk.rdi.essentials.general.command.impl.cloudinv;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.ERinv;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

public class RinvCommand extends BaseCommand {

    public RinvCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);

    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) ->
                execute(context.getSource()));
    }
    private int execute(CommandSource source) {
        String msgHelp[] = {
                "---RDI�Ʋֿ�ϵͳ(/rinv)---v1.3",
                //"/rinvput ����->�Ʋֿ� /rinvget �Ʋֿ�->����",
                //"/rinvshanchudelete ɾ���Ʋֿ����� /rinvls (--nbt) �о�������Ʒ",
                //"ʹ��--nbt�����Բ鿴��ƷJSON���ݱ�ǩ",
                //"[����]ǰ,ȷ������[����]��[û���κ���Ʒ].[�ϴ�]ǰ,ȷ��[�Ʋֿ�]��[û���κ���Ʒ]",
                "��o��л:KenRouKoro, SampsonnZ, pop751899"
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
            IFormattableTextComponent up=TextUtils.getClickableContentComp(player, ERinv.UPLOAD.text,ERinv.UPLOAD.cmd,ERinv.UPLOAD_HOVER.text);
            IFormattableTextComponent down=TextUtils.getClickableContentComp(player, ERinv.DOWNLOAD.text,ERinv.DOWNLOAD.cmd,ERinv.DOWNLOAD_HOVER.text);
            IFormattableTextComponent list=TextUtils.getClickableContentComp(player, ERinv.LIST.text,ERinv.LIST.cmd,ERinv.LIST_HOVER.text);
            TextUtils.sendChatMessage(player,up.append(down).append(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Command.SINGLE_SUCCESS;
    }

}