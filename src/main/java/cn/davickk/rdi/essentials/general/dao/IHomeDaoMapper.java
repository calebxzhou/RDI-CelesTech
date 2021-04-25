package cn.davickk.rdi.essentials.general.dao;

import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.model.Home;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IHomeDaoMapper {

    //按照玩家uuid寻找家
    List<Home> getByPlayerUuid(String uuid);
    //获取玩家 家数量
    int getRecordAmount(String uuid);
    //向总表中添加一个家
    void insertRecord(Home home);
    //家是否存在
    int exists (@Param("uuid")String uuid, @Param("homeName")String homeName);
    //家是否已激活
    int isActive(@Param("uuid")String uuid,@Param("homeName")String homeName);
    //有没有激活的家
    int hasActive(@Param("uuid")String uuid);
    //设置家激活状态
    void setActive(@Param("uuid")String uuid,@Param("homeName")String homeName,@Param("activ")int activ);
    //获取家位置
    //Location getLocation(@Param("uuid")String uuid,@Param("homeName")String homeName);
    //删除指定的家
    void delete(@Param("uuid")String uuid,@Param("homeName")String homeName);
    //按照uuid删除指定玩家的所有家
    void delAllByUuid(String uuid);

    void addComment(@Param("uuid")String uuid,@Param("homeName")String homeName,@Param("comments")String comment);

}
