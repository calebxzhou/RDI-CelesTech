package cn.davickk.rdi.essentials.general.command.impl.server;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.server.RestartTask;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.Timer;

public class VoteRestartCmd extends BaseCommand {
    //�Ѿ�ͶƱ���������Map����һ����UUID���ڶ������������
    private static final HashMap<String, String> votedPlayer = new HashMap<>();
    private boolean isGoingRestart=false;
    public static HashMap<String,String> getVotedPlayerMap(){
        return votedPlayer;
    }

    public VoteRestartCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        try {
            ServerPlayerEntity playerEntity= source.getPlayerOrException();
            if(isGoingRestart){
                sendMessage(playerEntity,"�������Ѿ�׼��������");
                return 1;
            }
            String playerUuid= playerEntity.getStringUUID();
            String playerName=playerEntity.getName().getString();
            MinecraftServer server=playerEntity.getServer();
            int playerAmount=server.getPlayerCount();
            if(playerAmount<3){
                sendMessage(playerEntity, "��������3��ʱ�޷�ʹ�ñ�����");
                return 1;
            }
            if(getVotedPlayerMap().containsKey(playerUuid)){
                sendMessage(playerEntity,"���Ѿ�ͶƱ���ˣ��벻Ҫ�ظ�ͶƱ");
                return 1;
            }
            //����֮����ͶƱ����
            int restartAtPlayerAmount= (int) (server.getPlayerCount()*(2.0/3.0));
            if(getVotedPlayerMap().size()>=restartAtPlayerAmount){
                sendGlobalMessage(server.getPlayerList(),"ͶƱ����������10��������������");

                new Timer().schedule(new RestartTask(server),10000L);
                return 1;
            }
            getVotedPlayerMap().put(playerUuid,playerName);
            sendMessage(playerEntity,"���ѳɹ�ͶƱ����");

            sendGlobalMessage(server.getPlayerList(),"��ǰ�Ѿ�ͶƱ����������У�"+getVotedPlayerMap().values().toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
