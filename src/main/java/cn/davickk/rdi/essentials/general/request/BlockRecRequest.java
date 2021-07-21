package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.dao.IBlockRecDaoMapper;
import cn.davickk.rdi.essentials.general.model.SingleBlockRecord;
import cn.davickk.rdi.essentials.general.util.DateTimeUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.ibatis.session.SqlSession;

public class BlockRecRequest {
    private final SqlSession SQL_SESSION = RDIEssentials.getSQLUtils().getSqlSession();
    private IBlockRecDaoMapper mapper;
    private SingleBlockRecord record;
    public BlockRecRequest(SingleBlockRecord record){
        this.record=record;
        mapper=SQL_SESSION.getMapper(IBlockRecDaoMapper.class);
    }

    public void insertRecord(){
        mapper.insertRecord(record);
        commit();
    }

    private void commit(){
        SQL_SESSION.commit();
    }
}
