package cn.davickk.rdi.essentials.general.command.impl.teleport;

public class TpacceptCommand {//extends BaseCommand {
}/*
    public TpacceptCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource())).then(Commands.argument("targetPlayer", EntityArgument.players()).executes(context -> execute(context.getSource(), EntityArgument.getPlayer(context, "targetPlayer"))));
    }


    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        ArrayList<TeleportRequest> requests = TeleportUtils.findRequest(player);
        if (requests.size() == 1) {
            TeleportUtils.acceptRequest(requests.get(0));
        } else if (requests.size() > 1) {
            sendMessage(player,new StringTextComponent("�кü�����ȡ����������ָ���ĸ���~�� ��233"));
            //sendMessage(player, "rdi-essentials.specify.player");
        } else {
            sendMessage(player,new StringTextComponent("����޻�ȡ��������"));
            //sendMessage(player, "tpa.rdi-essentials.no_request");
        }
        return Command.SINGLE_SUCCESS;
    }

    private int execute(CommandSource source, ServerPlayerEntity targetPlayer) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        TeleportRequest tpR = TeleportUtils.findRequest(player, targetPlayer);
        if (tpR != null) {
            TeleportUtils.acceptRequest(tpR);
        } else {
            sendMessage(player,new StringTextComponent("�޴�����id������"));
            //sendMessage(player, "tpa.rdi-essentials.not_found");
        }
        return Command.SINGLE_SUCCESS;
    }

}*/
