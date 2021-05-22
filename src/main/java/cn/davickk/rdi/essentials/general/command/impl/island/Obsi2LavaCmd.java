package cn.davickk.rdi.essentials.general.command.impl.island;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

public class Obsi2LavaCmd extends BaseCommand {
    public Obsi2LavaCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source){
        try {
            ServerPlayerEntity player=source.getPlayerOrException();
            BlockPos blockp= PlayerUtils.lookingAtBlock(player,false);
            if(blockp==null){
                sendMessage(player,"请对准黑曜石，而不是空气。");
                return 1;
            }
            BlockState blocks=player.getCommandSenderWorld().getBlockState(blockp);
            if(PlayerUtils.hasEnoughXPLvl(player,2)){
                sendMessage(player,"您的能力太强了，无法执行。");
                return Command.SINGLE_SUCCESS;
            }
            if(blocks.getBlock().getRegistryName().getPath().contains("obsidian")){
                sendMessage(player,"成功把黑曜石熔为岩浆。");
                //player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP,1.0f,0.5f);
                //PlayerUtils.givePlayerItem(player,"minecraft:lava_bucket",1);
                player.getCommandSenderWorld().setBlockAndUpdate(blockp, Fluids.LAVA.defaultFluidState().createLegacyBlock());
            }else{
                TranslationTextComponent blockname=new TranslationTextComponent(blocks.getBlock().getRegistryName().toString());
                sendMessage(player,"请将屏幕正中间的光标(形状为“十”)对准"+ EColor.PURPLE.code+"黑曜石"+EColor.RESET.code+
                        "，再执行本操作(当前对准的是:"+blockname.getString()+")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Command.SINGLE_SUCCESS;
    }
}
