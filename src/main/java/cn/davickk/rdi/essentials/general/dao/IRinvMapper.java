package cn.davickk.rdi.essentials.general.dao;

import cn.davickk.rdi.essentials.general.model.Rinv;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRinvMapper {
    void put(@Param("uuid") String uuid,
             @Param("playerName") String playerName,
             @Param("serializedNbt") String serializedNbt);
    List<Rinv> get(String uuid);
    void delete(String uuid);
}
