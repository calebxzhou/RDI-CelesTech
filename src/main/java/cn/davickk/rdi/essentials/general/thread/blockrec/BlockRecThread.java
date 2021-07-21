package cn.davickk.rdi.essentials.general.thread.blockrec;


import cn.davickk.rdi.essentials.general.model.SingleBlockRecord;
import cn.davickk.rdi.essentials.general.request.BlockRecRequest;
import cn.davickk.rdi.essentials.general.util.DateTimeUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.world.BlockEvent;

public class BlockRecThread extends Thread{
    private BlockEvent.EntityPlaceEvent placeEvent=null;
    private BlockEvent.BreakEvent breakEvent=null;

    public BlockRecThread(BlockEvent.EntityPlaceEvent event){
        this.placeEvent =event;
    }
    public BlockRecThread(BlockEvent.BreakEvent event){
        this.breakEvent =event;
    }
    @Override
    public void run(){


        try {
            SingleBlockRecord record=new SingleBlockRecord();
            Entity entity;
            BlockState blockState;
            BlockPos blockPos;
            //如果放置方块
            if(this.placeEvent!=null){
                entity= placeEvent.getEntity();
                blockState= placeEvent.getPlacedBlock();
                blockPos= placeEvent.getPos();
                record.setOprType(SingleBlockRecord.PLACE);
            }
            //如果破坏方块
            else if(this.breakEvent!=null){
                entity= breakEvent.getPlayer();
                blockState= breakEvent.getState();
                blockPos= breakEvent.getPos();
                record.setOprType(SingleBlockRecord.BRAKE);
            }
            else{
                record.setOprType(SingleBlockRecord.UNDEFINED);
                return;
            }


            String dim=entity.getCommandSenderWorld().dimension().location().toString();
            String blockType=new TranslationTextComponent(blockState.getBlock().getDescriptionId()).getString()+"("+blockState.getBlock().getRegistryName().toString()+")";
            String playerName=entity.getDisplayName().getString();

            record.setPlayer_name(playerName);
            record.setBlock_type(blockType);
            record.setDimension(dim);
            record.setPosX(blockPos.getX());
            record.setPosY(blockPos.getY());
            record.setPosZ(blockPos.getZ());
            record.setOpr_time(DateTimeUtils.getTimestampNow());

            new BlockRecRequest(record).insertRecord();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
