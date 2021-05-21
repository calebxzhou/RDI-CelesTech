package cn.davickk.rdi.essentials.general.command.impl.player;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

import java.text.DecimalFormat;

public class RtpsCommand extends BaseCommand {
    private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("########0.000");
    private static final long[] UNLOADED = new long[]{0};

    public RtpsCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }

    private int execute(CommandSource ctx) throws CommandSyntaxException {
        //for (ServerWorld dim : ctx.getServer().getWorlds())
        // sendTime(ctx, dim);//一般玩家可以忍受的延迟 120ms

        //String squarePattern25="§a■■■■■■■■■■■■■■■ §e■■■■■■■ §c■■■";
        double meanTickTime = mean(ctx.getServer().tickTimes) * 1.0E-6D;
        double stdTickTime = 120.0;
        double meanTPS = Math.min(1000.0 / meanTickTime, 20);
        double meanTPM=meanTPS*60*24;
        double ratio = meanTickTime / stdTickTime;
        long squares = Math.round(25 * ratio);
        String squarePattern1 = ">";
        String squaresToSend = "§a";

        for (int i = 0; i <= squares; ++i) {
            squaresToSend += squarePattern1;
            if (i == 15)
                squaresToSend += "§e";
            if (i == 22)
                squaresToSend += "§c";
        }
        ctx.sendSuccess(new StringTextComponent(
                        "负载[" + Math.round(ratio * 100) + "%]" + squaresToSend),
                false);
        ctx.sendSuccess(new StringTextComponent("延迟 " + Math.round(meanTickTime) + "ms"), false);
        //TIME_FORMATTER.format(meanTickTime)+","+TIME_FORMATTER.format(meanTPS)),false);
        ctx.sendSuccess(new StringTextComponent("服务器每分钟处理量 "+meanTPM),false);
        return Command.SINGLE_SUCCESS;
    }

    /* private static int sendTime(CommandSource cs, ServerWorld dim) throws CommandSyntaxException
     {
         long[] times = cs.getServer().getTickTime(dim.func_234923_W_());

         if (times == null) // Null means the world is unloaded. Not invalid. That's taken car of by DimensionArgument itself.
             times = UNLOADED;

         final Registry<DimensionType> reg = cs.func_241861_q().func_243612_b(Registry.DIMENSION_TYPE_KEY);
         double worldTickTime = mean(times) * 1.0E-6D;
         double worldTPS = Math.min(1000.0 / worldTickTime, 20);
         cs.sendFeedback(
                 new TranslationTextComponent("commands.forge.tps.summary.named",
                         dim.func_234923_W_().func_240901_a_().toString(),
                         reg.getKey(dim.func_230315_m_()),
                         TIME_FORMATTER.format(worldTickTime),
                         TIME_FORMATTER.format(worldTPS)), false);

         return 1;
     }*/
    private static long mean(long[] values) {
        long sum = 0L;
        for (long v : values)
            sum += v;
        return sum / values.length;
    }
}
