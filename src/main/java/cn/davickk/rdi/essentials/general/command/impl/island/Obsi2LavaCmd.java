package cn.davickk.rdi.essentials.general.command.impl.island;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class Obsi2LavaCmd extends BaseCommand {
    public Obsi2LavaCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        BlockPos blockp= PlayerUtils.lookingAtBlock(player,false);
        BlockState blocks=player.getServerWorld().getBlockState(blockp);
        if(blocks.getBlock().getRegistryName().getPath().contains("obsidian")){
            sendMessage(player,"�ɹ��Ѻ���ʯ��Ϊ�ҽ���");
            player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP,1.0f,0.5f);
            //PlayerUtils.givePlayerItem(player,"minecraft:lava_bucket",1);
            player.getServerWorld().setBlockState(blockp, Fluids.LAVA.getDefaultState().getBlockState());
        }else{
            TranslationTextComponent blockname=new TranslationTextComponent(blocks.getBlock().getTranslationKey());
            sendMessage(player,"��Ӷ�/�����׼"+ EColor.PURPLE.code+"����ʯ�Ĳ���"+EColor.RESET.code+
                    "����ִ�б�����(��ǰ��׼����:"+blockname.getString()+"������:" +PlayerUtils.facing(player)+")");
        }
        return Command.SINGLE_SUCCESS;
    }
}
