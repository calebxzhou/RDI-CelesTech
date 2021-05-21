package cn.davickk.rdi.essentials.general.thread.blockrec;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.dao.IBlockRecDaoMapper;
import cn.davickk.rdi.essentials.general.model.SingleBlockRecord;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ReadRecThread extends Thread{
    private PlayerEntity player;
    private BlockPos blockPos;
    public ReadRecThread(PlayerEntity player, BlockPos blockPos){
        this.player=player;
        this.blockPos=blockPos;
    }
    public void run(){
        try {
            int x=blockPos.getX();
            int y=blockPos.getY();
            int z=blockPos.getZ();

            IBlockRecDaoMapper mapper= RDIEssentials.getSQLUtils().getSqlSession().getMapper(IBlockRecDaoMapper.class);
            List<SingleBlockRecord> records=mapper.readRecordOnXyz(x,y,z);
            TextUtils.sendChatMessage(player,
                    String.format("----查询结果 (%d,%d,%d)----",x,y,z));
            String formattedTime = "";
            String formattedTimePrefix = "";

            LocalDateTime nowTime=LocalDateTime.now();

            for(SingleBlockRecord record:records){
                Timestamp time=record.getOpr_time();
                LocalDateTime recordTime=time.toLocalDateTime();


                


                TextUtils.sendChatMessage(player,
                        String.format("[%d]%s %s%s%s",
                                record.getRecord_id(),
                                record.getOpr_time(),
                                record.getPlayer_name(),
                                record.getOprType(),
                                record.getBlock_type()));
                //不是今年，显示完整时间
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
