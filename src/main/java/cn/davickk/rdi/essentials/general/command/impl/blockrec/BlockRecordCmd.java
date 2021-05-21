package cn.davickk.rdi.essentials.general.command.impl.blockrec;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.rinv.RinvThread;
import cn.davickk.rdi.essentials.general.thread.tick.TickTask;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

public class BlockRecordCmd extends BaseCommand {
    private static Timer timer=new Timer();
    private List<String> oprationList=new ArrayList();
    private final SuggestionProvider<CommandSource> SUGGESTIONS_PROVIDER
            = (context, builder) -> ISuggestionProvider.suggest
            (oprationList, builder);
    public BlockRecordCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
        oprationList.add("inspect");
    }
    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()))
                .then(Commands.argument("opration", StringArgumentType.string())
                        .suggests(SUGGESTIONS_PROVIDER)
                        .executes((context) ->
                                execute(context.getSource(), StringArgumentType.getString(context, "opration"))
                        )
                );
    }
    public static Timer getTimer(){
        return timer;
    }
    private int execute(CommandSource source) {
        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source, String opration) throws CommandSyntaxException {
        try {
            ServerPlayerEntity player=source.getPlayerOrException();
            if(!oprationList.contains(opration)){
                sendMessage(player,"无法识别本指令，请检查是否有输入错误。");
                return Command.SINGLE_SUCCESS;
            }
            HashMap<String, PlayerEntity> map=ServerUtils.getPlayerInspectingMap();
            if(opration.equals("inspect")){
                if(map.containsKey(player.getStringUUID())){
                    map.remove(player.getStringUUID());
                    sendMessage(player,"您已关闭记录查询模式。");
                    timer.cancel();
                    return Command.SINGLE_SUCCESS;
                }else{
                    map.put(player.getStringUUID(),player);
                    sendMessage(player,"您已开启记录查询模式：");
                    timer.schedule(new TickTask(player), 0, 1000);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Command.SINGLE_SUCCESS;
    }
}
