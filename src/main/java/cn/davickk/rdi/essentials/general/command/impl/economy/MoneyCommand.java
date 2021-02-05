package cn.davickk.rdi.essentials.general.command.impl.economy;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.util.BankUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class MoneyCommand extends BaseCommand {

    public MoneyCommand(String command, int permissionLevel, boolean enabled) {
        super(command, permissionLevel, enabled);

    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context)
                -> execute(context.getSource()))
                .then(Commands.argument
                        ("NoArgs", StringArgumentType.string())
                        .executes((context)
                                -> execute(context.getSource())));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        try {
            ServerPlayerEntity player = source.asPlayer();
            sendMessage(player, "您好，" + player.getDisplayName().getString());
            double money = BankUtils.balance(player);
            if (money < 0)
                TextUtils.sendChatMessage(player, "您似乎没有开户,请通过/kaihu或者/creAcc进行开户");
            sendMessage(player, "余额为" + money + "RDI$");
        } catch (Exception se) {
            se.printStackTrace();
        }
        return Command.SINGLE_SUCCESS;
    }
    /*public static Integer addPlayerAccount(String uuid, float money){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer rdiID = null;
        try {
            tx = session.beginTransaction();
            //Employee employee = new Employee(fname, lname, salary);
            PlayerAccount account=new PlayerAccount(uuid,money);
            rdiID = (Integer) session.save(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rdiID;
    }*/
}
