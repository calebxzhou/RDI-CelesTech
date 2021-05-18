package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.thread.player.TechRollT;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;


public class RollCommand extends BaseCommand {

    public RollCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()))
                .then(Commands.argument("homeName", IntegerArgumentType.integer())
                        //.suggests(ModUtils.HOME_SUGGEST)
                        .executes(
                                (context) ->
                                        execute(context.getSource(),
                                                IntegerArgumentType.getInteger(context,"homeName"))
                        )
                );
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        String z1="";
        String z3="";
        String z5="";
        String z10="";
        ServerPlayerEntity player= source.getPlayerOrException();

        if(PlayerUtils.hasEnoughXPLvl(player,3))
            z1=EColor.ORANGE.code+"[ת1��] ";
        if(PlayerUtils.hasEnoughXPLvl(player,9))
            z3=EColor.PINK.code+"[ת3��] ";
        if(PlayerUtils.hasEnoughXPLvl(player,15))
            z5=EColor.AQUA.code+"[ת5��] ";
        if(PlayerUtils.hasEnoughXPLvl(player,30))
            z10=EColor.GOLD.code+"[ת10��] ";
        IFormattableTextComponent t1
                = TextUtils.getClickableContentComp(player, z1,"/rroll 1","��Ҫ����");
        IFormattableTextComponent t5
                = TextUtils.getClickableContentComp(player,z5,"/rroll 5","��Ҫ����");
        IFormattableTextComponent t10
                = TextUtils.getClickableContentComp(player, z10,"/rroll 10","��Ҫ����");
        IFormattableTextComponent t3
                = TextUtils.getClickableContentComp(player, z3,"/rroll 3","��Ҫ����");

        TextUtils.sendChatMessage(player, t1.append(t3).append(t5).append(t10));

        return Command.SINGLE_SUCCESS;
    }
    private int execute(CommandSource source,int count){
        try {
            if(PlayerUtils.minusXPLvl(source.getPlayerOrException(), count*3)){
                TextUtils.sendChatMessage(source.getPlayerOrException(), "ת�������У���Ҫ�˳���Ϸ����");
                System.out.println(source.getPlayerOrException().getDisplayName().getString()+"���ڳ齱");
                ServerUtils.startThread(new TechRollT(source.getPlayerOrException(),count));
            }
            else
                TextUtils.sendChatMessage(source.getPlayerOrException(), "���鲻��");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return Command.SINGLE_SUCCESS;
    }

}
