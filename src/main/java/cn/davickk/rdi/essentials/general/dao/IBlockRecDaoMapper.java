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
                                                  @Param("rangeStartFrom") int rangeStartFrom,
                                                  @Param("showRowsAmount") int showRowsAmount,
                                               @Param("isDesc") boolean isDesc);

    List<SingleBlockRecord> queryByPlayer(@Param("playerName") String playerName);

    SingleBlockRecord queryById(long id);


}
