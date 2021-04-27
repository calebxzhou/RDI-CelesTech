package cn.davickk.rdi.essentials.general.dao;

import cn.davickk.rdi.essentials.general.model.Home;
import cn.davickk.rdi.essentials.general.model.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IHomeDaoMapper {

    //�������uuidѰ�Ҽ�
    List<Home> getByPlayerUuid(String uuid);
    //��ȡ��� ������
    int getRecordAmount(String uuid);
    //���ܱ������һ����
    void insertRecord(Home home);
    //���Ƿ����
    int exists (@Param("uuid")String uuid, @Param("homeName")String homeName);
    //���Ƿ��Ѽ���
    int isActive(@Param("uuid")String uuid,@Param("homeName")String homeName);
    //��û�м���ļ�
    int hasActive(@Param("uuid")String uuid);

    Location getLocation(@Param("uuid")String uuid,@Param("homeName")String homeName);

    //���üҼ���״̬
    void setActive(@Param("uuid")String uuid,@Param("homeName")String homeName,@Param("activ")int activ);
    //��ȡ��λ��
    //Location getLocation(@Param("uuid")String uuid,@Param("homeName")String homeName);
    //ɾ��ָ���ļ�
    void delete(@Param("uuid")String uuid,@Param("homeName")String homeName);
    //����uuidɾ��ָ����ҵ����м�
    void delAllByUuid(String uuid);

    void addComment(@Param("uuid")String uuid,@Param("homeName")String homeName,@Param("comments")String comment);

    void rename(
            @Param("uuid") String uuid,
            @Param("homeName") String homeName,
                @Param("newHomeName")String newHomeName

                );
    void locate(@Param("uuid")String uuid,
                @Param("homeName") String homeName,
                @Param("x")double x,
                @Param("y")double y,
                @Param("z")double z
                );
}
