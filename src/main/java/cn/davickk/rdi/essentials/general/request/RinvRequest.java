package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.dao.IRinvMapper;
import cn.davickk.rdi.essentials.general.model.Rinv;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RinvRequest {
    private final SqlSession SQL_SESSION = RDIEssentials.getSQLUtils().getSqlSession2();
    private IRinvMapper RINV_MAPPER;
    private final ServerPlayerEntity PLAYER;
    private final String UUID,PLAYER_NAME;
    public RinvRequest(ServerPlayerEntity player){
        RINV_MAPPER = SQL_SESSION.getMapper(IRinvMapper.class);
        PLAYER=player;
        UUID=player.getUniqueID().toString();
        PLAYER_NAME=player.getDisplayName().getString();
    }
    public void put(String uuid,String playerName,String serializedNbt){
        RINV_MAPPER.put(uuid, playerName, serializedNbt);
        SQL_SESSION.commit();
    }
    public List<Rinv> get(String uuid){
        return RINV_MAPPER.get(uuid);
    }
    public void delete(String uuid){
        RINV_MAPPER.delete(uuid);
        SQL_SESSION.commit();
    }
}
