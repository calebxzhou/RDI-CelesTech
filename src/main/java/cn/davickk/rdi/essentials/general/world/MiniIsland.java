package cn.davickk.rdi.essentials.general.world;

import cn.davickk.rdi.essentials.RDIEssentials;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;

import java.util.Random;

public class MiniIsland {}//extends Feature<NoFeatureConfig> {
    /*@Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int x=pos.getX();
        int y=pos.getY();
        int z=pos.getZ();
        Template struTmp=reader.getWorld().getStructureTemplateManager()
                .getTemplateDefaulted(new ResourceLocation(RDIEssentials.MODID,"islandd"));
        struTmp.func_237144_a_(reader.getWorld(),pos,
                new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE));
        struTmp.getSize();
         ArrayList<TileEntityChest> chests = new ArrayList<>();
        BlockPos size = structureTemplate.getSize();
        for(int x=0;x<=size.getX();x++){
            for(int y=0;y<=size.getY();y++){
                for(int z=0;z<=size.getZ();z++){
                    BlockPos tmp = new BlockPos(posX+x,posY+y,posZ+z);
                    if(worldIn.getTileEntity(tmp)!=null){
                        if(worldIn.getTileEntity(tmp) instanceof TileEntityChest){
                            chests.add((TileEntityChest) worldIn.getTileEntity(tmp));
                        }
                    }
                }
            }
        }
        //fill chests
        for(TileEntityChest chest:chests){
            ArrayList<ItemStack> rewards = new ArrayList<>();
            rewards = RewardHelper.getCloudMiniTempleRewards(rand);
            for(int i = 0;i<=rewards.size();i++) {
                chest.setInventorySlotContents(rand.nextInt(15), rewards.get(rand.nextInt(rewards.size())));
            }
        }
        return true;
     */



