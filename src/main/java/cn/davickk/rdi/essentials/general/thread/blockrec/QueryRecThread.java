package cn.davickk.rdi.essentials.general.thread.blockrec;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.dao.IBlockRecDaoMapper;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.model.DetailedBlockRecord;
import cn.davickk.rdi.essentials.general.model.SingleBlockRecord;
import cn.davickk.rdi.essentials.general.util.DateTimeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class QueryRecThread extends Thread{
    private PlayerEntity player;
    private BlockPos blockPos;
    private DetailedBlockRecord record=null;
    public QueryRecThread(PlayerEntity player, BlockPos blockPos){
        this.player=player;
        this.blockPos=blockPos;
    }
    public QueryRecThread(PlayerEntity player, DetailedBlockRecord record){
        this.record=record;
        this.blockPos=new BlockPos(record.getX(),record.getY(),record.getZ());
        this.player=player;
    }
    public void run(){
        try {
            if(record!=null){

                return;
            }
            int x=blockPos.getX();
            int y=blockPos.getY();
            int z=blockPos.getZ();
            TextUtils.sendChatMessage(player,
                    String.format("----查询结果 (%d,%d,%d)----",x,y,z));
            PlayerUtils.sendLoading(player);
            IBlockRecDaoMapper mapper= RDIEssentials.getSQLUtils().getSqlSession().getMapper(IBlockRecDaoMapper.class);


            List<SingleBlockRecord> records=mapper.queryByCoord(x,y,z);
            if(records.isEmpty()) {
                TextUtils.sendChatMessage(player,"没有在这里找到结果。");
            }
            String formattedTime = "";
            String formattedTimePrefix = "";

            LocalDateTime nowTime=LocalDateTime.now();

            for(SingleBlockRecord record:records){
                Timestamp time=record.getOpr_time();
                LocalDateTime recordTime=time.toLocalDateTime();
                //今天昨天前天xx:xx etc.
                String timec= DateTimeUtils.getComparedDateTime(recordTime,nowTime);
                String oprtype="?";
                long reqid=record.getRecord_id();
                //放置/毁坏类型
                if(record.getOprType()==SingleBlockRecord.BRAKE)
                    oprtype= EColor.RED.code+ "毁"+EColor.RESET.code;
                if(record.getOprType()==SingleBlockRecord.PLACE)
                    oprtype=EColor.BRIGHT_GREEN.code+"置"+EColor.RESET.code;

                TextUtils.sendChatMessage(player,
                        String.format("- %s %s %s %s",
                                timec,
                                record.getPlayer_name(),
                                oprtype,
                                record.getBlock_type()));
                //不是今年，显示完整时间
            }
            TextUtils.sendChatMessage(player,"[<<上一页]----<第%d/%d页>----[下一页>>]");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void getDetailedOprations(){
        String str_orderBy="<排序>",
                str_filter="<筛选>";

    }
}
