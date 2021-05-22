package cn.davickk.rdi.essentials.general.dao;


import cn.davickk.rdi.essentials.general.model.SingleBlockRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBlockRecDaoMapper {
    void insertRecord(SingleBlockRecord record);
    List<SingleBlockRecord> queryByCoord(@Param("posX") int x,
                                         @Param("posY") int y,
                                         @Param("posZ") int z);

    List<SingleBlockRecord> queryByCoordRanged(@Param("posX") int x,
                                                  @Param("posY") int y,
                                                  @Param("posZ") int z,
                                                  @Param("rangeMin") int rangeMin,
                                                  @Param("rangeMax") int rangeMax)));

    List<SingleBlockRecord> query
SingleBlockRecord queryById(long id);


}
