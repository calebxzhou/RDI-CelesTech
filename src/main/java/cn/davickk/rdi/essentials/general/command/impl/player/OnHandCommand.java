package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

public class OnHandCommand extends BaseCommand {
    public OnHandCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context)
                -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        try {
            ItemStack itm = player.getHeldItemMainhand();
            Item it = itm.getItem();
            int amount = itm.getCount();
            CompoundNBT nbt = itm.getTag();
            sendMessage(player, new StringTextComponent
                    ("�����������ŵ���" + amount + "��" + itm.getDisplayName().getString()));
            sendMessage(player, it.getRegistryName().toString());
            sendMessage(player, it.getTranslationKey());

            sendMessage(player, (TextComponent) itm.getTextComponent());
            if (nbt == null)
                sendMessage(player, new StringTextComponent("����Ʒû�и�������"));
            else
                sendMessage(player, new StringTextComponent("�������ݣ�" + nbt.getString()));


        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Location ploc = new Location(player);
       // if(player.getServerWorld().getWorldInfo())

        double x=ploc.x;
        double y=ploc.y;
        double z=ploc.z;
        TextComponent text=new StringTextComponent
                ("������������磬��Ӧ����"+Math.round(x/8.0)
                        +","+Math.round(y)+","+Math.round(z/8.0));
        TextComponent text2=new StringTextComponent
                ("������ڵ�������Ӧ������"+Math.round(x*8.0)
                        +","+Math.round(y)+","+Math.round(z*8.0));
        sendMessage(player,text);
        sendMessage(player,text2);*/
        return Command.SINGLE_SUCCESS;
    }

}
