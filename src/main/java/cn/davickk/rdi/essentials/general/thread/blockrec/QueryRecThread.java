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
                    String.format("----��ѯ��� (%d,%d,%d)----",x,y,z));
            PlayerUtils.sendLoading(player);
            IBlockRecDaoMapper mapper= RDIEssentials.getSQLUtils().getSqlSession().getMapper(IBlockRecDaoMapper.class);


            List<SingleBlockRecord> records=mapper.queryByCoord(x,y,z);
            if(records.isEmpty()) {
                TextUtils.sendChatMessage(player,"û���������ҵ������");
            }
            String formattedTime = "";
            String formattedTimePrefix = "";

            LocalDateTime nowTime=LocalDateTime.now();

            for(SingleBlockRecord record:records){
                Timestamp time=record.getOpr_time();
                LocalDateTime recordTime=time.toLocalDateTime();
                //��������ǰ��xx:xx etc.
                String timec= DateTimeUtils.getComparedDateTime(recordTime,nowTime);
                String oprtype="?";
                long reqid=record.getRecord_id();
                //����/�ٻ�����
                if(record.getOprType()==SingleBlockRecord.BRAKE)
                    oprtype= EColor.RED.code+ "��"+EColor.RESET.code;
                if(record.getOprType()==SingleBlockRecord.PLACE)
                    oprtype=EColor.BRIGHT_GREEN.code+"��"+EColor.RESET.code;

                TextUtils.sendChatMessage(player,
                        String.format("- %s %s %s %s",
                                timec,
                                record.getPlayer_name(),
                                oprtype,
                                record.getBlock_type()));
                //���ǽ��꣬��ʾ����ʱ��
            }
            TextUtils.sendChatMessage(player,"[<<��һҳ]----<��%d/%dҳ>----[��һҳ>>]");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void getDetailedOprations(){
        String str_orderBy="<����>",
                str_filter="<ɸѡ>";

    }
}
