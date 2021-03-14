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
                "---RDI云仓库系统(/rinv)---v1.3",
                //"/rinvput 背包->云仓库 /rinvget 云仓库->背包",
                //"/rinvshanchudelete 删除云仓库数据 /rinvls (--nbt) 列举所有物品",
                //"使用--nbt参数以查看物品JSON数据标签",
                //"[下载]前,确保您的[背包]中[没有任何物品].[上传]前,确保[云仓库]中[没有任何物品]",
                "§o感谢:KenRouKoro, SampsonnZ, pop751899"
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