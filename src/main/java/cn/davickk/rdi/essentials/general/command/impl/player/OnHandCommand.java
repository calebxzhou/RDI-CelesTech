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
                    ("您的手上拿着的是" + amount + "个" + itm.getDisplayName().getString()));
            sendMessage(player, it.getRegistryName().toString());
            sendMessage(player, it.getTranslationKey());

            sendMessage(player, (TextComponent) itm.getTextComponent());
            if (nbt == null)
                sendMessage(player, new StringTextComponent("该物品没有附加数据"));
            else
                sendMessage(player, new StringTextComponent("附加数据：" + nbt.getString()));


        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Location ploc = new Location(player);
       // if(player.getServerWorld().getWorldInfo())

        double x=ploc.x;
        double y=ploc.y;
        double z=ploc.z;
        TextComponent text=new StringTextComponent
                ("如果你在主世界，对应地狱"+Math.round(x/8.0)
                        +","+Math.round(y)+","+Math.round(z/8.0));
        TextComponent text2=new StringTextComponent
                ("如果你在地狱，对应主世界"+Math.round(x*8.0)
                        +","+Math.round(y)+","+Math.round(z*8.0));
        sendMessage(player,text);
        sendMessage(player,text2);*/
        return Command.SINGLE_SUCCESS;
    }

}
