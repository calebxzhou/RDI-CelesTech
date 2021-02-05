package cn.davickk.rdi.essentials.general.command.impl.teleport;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class TpahereCommand extends BaseCommand {

    public TpahereCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.then(Commands.argument("targetPlayer", EntityArgument.players()).executes(context -> execute(context.getSource(), EntityArgument.getPlayer(context, "targetPlayer"))));
    }

    private int execute(CommandSource source, ServerPlayerEntity targetPlayer) throws CommandSyntaxException {
/*        ServerPlayerEntity player = source.asPlayer();
        EssentialPlayer eslPlayer = DataManager.getPlayer(player);

        if (player == targetPlayer) {
            sendMessage(player, "tpa.rdi-essentials.self");
            return Command.SINGLE_SUCCESS;
        }

        long cooldown = eslPlayer.getUsage().getTeleportCooldown("tpa", ModConfig.tpa_cooldown);
        if (cooldown != 0) {
            TextUtils.sendChatMessage(player, EnumLang.TELEPORT_COOLDOWN.translateColored(EnumColor.DARK_RED, EnumLang.GENERIC.translateColored(EnumColor.RED, cooldown)));
            return Command.SINGLE_SUCCESS;
        }

        eslPlayer.getUsage().setCommandUsage("tpa");
        eslPlayer.saveData();
        if (requestTeleport(player, targetPlayer, player, ModConfig.tpa_timeout)) {
            sendMessage(player, "tpa.rdi-essentials.request", targetPlayer.getDisplayName());
            sendMessage(targetPlayer, "tpahere.rdi-essentials.request.target", player.getDisplayName());

            ClickEvent clickEventAccept = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tpaccept " + player.getDisplayName().getString());
            HoverEvent eventHoverAccept = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("tpa.rdi-essentials.request.target.accept.hover", "/tpaccept " + player.getDisplayName().getString()));
            TextComponent textAccept = new StringTextComponent("/tpaccept");
            textAccept.getStyle().setClickEvent(clickEventAccept);
            textAccept.getStyle().setHoverEvent(eventHoverAccept);
            sendMessage(targetPlayer, "tpa.rdi-essentials.request.target.accept", textAccept);

            ClickEvent clickEventDeny = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tpdeny " + player.getDisplayName().getString());
            HoverEvent eventHoverDeny = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("tpa.rdi-essentials.request.target.deny.hover", "/tpdeny " + player.getDisplayName().getString()));
            TextComponent textDeny = new StringTextComponent("/tpdeny");
            textDeny.getStyle().setClickEvent(clickEventDeny);
            textDeny.getStyle().setHoverEvent(eventHoverDeny);

            sendMessage(targetPlayer, "tpa.rdi-essentials.request.target.deny", textDeny);
        }
*/
        return Command.SINGLE_SUCCESS;
    }

}
