package cn.davickk.rdi.essentials.general.command.impl.island;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

public class Water2IceCmd extends BaseCommand {
    public Water2IceCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.getPlayerOrException();
        if(!PlayerUtils.minusXPLvl(player,10)){
            TextUtils.sendChatMessage(player,"执行本操作需要10经验，您的经验不足。");
            return Command.SINGLE_SUCCESS;
        }
        BlockPos blockp= PlayerUtils.lookingAtBlock(player,true);
        BlockState blocks=player.getCommandSenderWorld().getBlockState(blockp);
        if(blocks.getBlock().getRegistryName().getPath().contains("water")){
            sendMessage(player,"成功把水冻住了。");
            player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP,1.0f,0.5f);
            player.getCommandSenderWorld().setBlockState(blockp, Blocks.ICE.getDefaultState());
        }else{
            TranslationTextComponent blockname=new TranslationTextComponent(blocks.getBlock().getTranslationKey());
            sendMessage(player,"请从东/南向对准"+ EColor.AQUA.code+"冰块"+EColor.RESET.code +
                    "，再执行本操作。(当前对准的是"+blockname.getString()+"，朝向:"
                    +PlayerUtils.facing(player)+")");
        }
        return Command.SINGLE_SUCCESS;
    }
}
