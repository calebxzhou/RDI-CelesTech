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
    //已经投票重启的玩家Map，第一个是UUID，第二个是玩家名称
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
                sendMessage(playerEntity,"服务器已经准备重启了");
                return 1;
            }
            String playerUuid= playerEntity.getStringUUID();
            String playerName=playerEntity.getName().getString();
            MinecraftServer server=playerEntity.getServer();
            int playerAmount=server.getPlayerCount();
            if(playerAmount<3){
                sendMessage(playerEntity, "人数少于3人时无法使用本功能");
                return 1;
            }
            if(getVotedPlayerMap().containsKey(playerUuid)){
                sendMessage(playerEntity,"您已经投票过了，请不要重复投票");
                return 1;
            }
            //三分之二人投票重启
            int restartAtPlayerAmount= (int) (server.getPlayerCount()*(2.0/3.0));
            if(getVotedPlayerMap().size()>=restartAtPlayerAmount){
                sendGlobalMessage(server.getPlayerList(),"投票人数已满，10秒后服务器将重启");

                new Timer().schedule(new RestartTask(server),10000L);
                return 1;
            }
            getVotedPlayerMap().put(playerUuid,playerName);
            sendMessage(playerEntity,"您已成功投票重启");

            sendGlobalMessage(server.getPlayerList(),"当前已经投票重启的玩家有："+getVotedPlayerMap().values().toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
