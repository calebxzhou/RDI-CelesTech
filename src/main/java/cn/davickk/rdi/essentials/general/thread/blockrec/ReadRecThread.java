package cn.davickk.rdi.essentials.general.thread.blockrec;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.dao.IBlockRecDaoMapper;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.model.SingleBlockRecord;
import cn.davickk.rdi.essentials.general.util.DateTimeUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

            TextUtils.sendChatMessage(player,
                    String.format("----查询结果 (%d,%d,%d)----",x,y,z));
            List<SingleBlockRecord> records=mapper.readRecordOnXyz(x,y,z);
            if(records.isEmpty()) {
                TextUtils.sendChatMessage(player,"没有在这里找到结果。");
            }
            String formattedTime = "";
            String formattedTimePrefix = "";

            LocalDateTime nowTime=LocalDateTime.now();

            for(SingleBlockRecord record:records){
                Timestamp time=record.getOpr_time();
                LocalDateTime recordTime=time.toLocalDateTime();
                String timec= DateTimeUtils.getComparedDateTime(recordTime,nowTime);
                String oprtype="?";
                if(record.getOprType()==SingleBlockRecord.BRAKE)
                    oprtype= EColor.RED.code+ "毁"+EColor.RESET.code;
                if(record.getOprType()==SingleBlockRecord.PLACE)
                    oprtype=EColor.BRIGHT_GREEN.code+"置"+EColor.RESET.code;

                TextUtils.sendChatMessage(player,
                        String.format("#%d %s %s %s %s",
                                record.getRecord_id(),
                                timec,
                                record.getPlayer_name(),
                                oprtype,
                                record.getBlock_type()));
                //不是今年，显示完整时间
            }
            TextUtils.sendChatMessage(player,"----------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
