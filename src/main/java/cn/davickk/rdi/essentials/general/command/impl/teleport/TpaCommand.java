package cn.davickk.rdi.essentials.general.command.impl.teleport;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.thread.teleport.SendTpaRequestT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TpaCommand extends BaseCommand {

    public TpaCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.then(Commands.argument("targetPlayer", EntityArgument.players()).executes(context -> execute(context.getSource(), EntityArgument.getPlayer(context, "targetPlayer"))));
    }

    private int execute(CommandSource source, ServerPlayerEntity toPlayer) throws CommandSyntaxException {
        ServerPlayerEntity fromPlayer = source.getPlayerOrException();
        if(fromPlayer==toPlayer){
            TextUtils.sendChatMessage(fromPlayer,"身体被一股奇怪的力量吸住了，动弹不得.....");
            fromPlayer.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,10*20,5));
            return Command.SINGLE_SUCCESS;
        }
        TextUtils.sendChatMessage(fromPlayer,"传送请求已发送给"+toPlayer.getDisplayName().getString());
        ServerUtils.startThread(new SendTpaRequestT(fromPlayer,toPlayer));
        /*EssentialPlayer eslPlayer = DataManager.getPlayer(player);

        if (player == targetPlayer) {
            //sendMessage(player, "tpa.rdi-essentials.self");
            sendMessage(player,new StringTextComponent("你搁这原地TP呢？"));
            return Command.SINGLE_SUCCESS;
        }

        long cooldown = eslPlayer.getUsage().getTeleportCooldown("tpa", ModConfig.tpa_cooldown);
        if (cooldown != 0) {
            TextUtils.sendChatMessage(player, EnumLang.TELEPORT_COOLDOWN.translateColored(EnumColor.DARK_RED, EnumLang.GENERIC.translateColored(EnumColor.RED, cooldown)));
            return Command.SINGLE_SUCCESS;
        }

        eslPlayer.getUsage().setCommandUsage("tpa");
        eslPlayer.saveData();

        if (requestTeleport(player, player, targetPlayer, ModConfig.tpa_timeout)) {
            sendMessage(player,new StringTextComponent("已经向"+targetPlayer.getDisplayName().getString()+"发送坐标请求"));
            //sendMessage(player, "tpa.rdi-essentials.request", targetPlayer.getDisplayName());
            sendMessage(targetPlayer,new StringTextComponent(player.getDisplayName().getString()+"想要获取你的坐标"));
            //sendMessage(targetPlayer, "tpa.rdi-essentials.request.target", player.getDisplayName());
            ClickEvent clickEventAccept = new ClickEvent(
                    ClickEvent.Action.SUGGEST_COMMAND, "/tpaccept " + player.getDisplayName().getString());
            HoverEvent eventHoverAccept = new HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    new StringTextComponent( player.getDisplayName().getString() )  );
                   /* new TranslationTextComponent(
                            "tpa.rdi-essentials.request.target.accept.hover",
                            "/tpaccept "
                                    + player.getDisplayName().getString()));
            TextComponent textAccept = new StringTextComponent("同意/tpaccept");
            textAccept.getStyle().setClickEvent(clickEventAccept);
            textAccept.getStyle().setHoverEvent(eventHoverAccept);
            sendMessage(targetPlayer,textAccept);
            //sendMessage(targetPlayer, "tpa.rdi-essentials.request.target.accept", textAccept);

            ClickEvent clickEventDeny = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tpdeny " + player.getDisplayName().getString());
            HoverEvent eventHoverDeny = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("tpa.rdi-essentials.request.target.deny.hover", "/tpdeny " + player.getDisplayName().getString()));
            TextComponent textDeny = new StringTextComponent("拒绝/tpdeny");
            textDeny.getStyle().setClickEvent(clickEventDeny);
            textDeny.getStyle().setHoverEvent(eventHoverDeny);
            sendMessage(targetPlayer,textDeny);
            //sendMessage(targetPlayer, "tpa.rdi-essentials.request.target.deny", textDeny);
        }*/

        return Command.SINGLE_SUCCESS;
    }

}
