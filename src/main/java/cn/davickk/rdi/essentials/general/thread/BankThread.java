package cn.davickk.rdi.essentials.general.thread;

import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.UUID;

public class BankThread extends Thread {
    public ServerPlayerEntity spe;
    public double money;
    public String stm;
    public String msg;

    public int opr;
    public static final int DEPOSIT = 1;
    public static final int WITHDRAW = 2;
    public static final int getBALANCE = 3;
    public static final int setBALANCE = 4;

    public BankThread(ServerPlayerEntity spe, int opr, double money, String msg) {
        this.money = money;
        this.spe = spe;
        this.msg = msg;
        this.opr = opr;
    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = null;//DriverManager.getConnection(DB_URL, USR, PWD);
            UUID u = spe.getUniqueID();
            if (opr == this.getBALANCE) {
                ResultSet rs = c.prepareStatement(stm).executeQuery();
                while (rs.next()) {
                    money = rs.getDouble("balance");
                    TextUtils.sendChatMessage(spe, new StringTextComponent("你有" + money + "RDI$"));
                }
            } else if (opr == this.setBALANCE) {
                String stm = "UPDATE eco SET balance=" + money + ",player_name=\'"
                        + spe.getDisplayName().getString() + "\' WHERE uuid=\'" + u + "\'";
                ResultSet rs = c.prepareStatement(stm).executeQuery();
                while (rs.next()) {
                    money = rs.getDouble("balance");
                    TextUtils.sendChatMessage(spe, new StringTextComponent("你有" + money + "RDI$"));
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
