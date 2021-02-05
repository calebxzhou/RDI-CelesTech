package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.enums.EBankResult;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class BankUtils {
    public static int createAccount(ServerPlayerEntity spe) {
        int[] result = {-1};
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection(DB_URL, USR, PWD);
                UUID u = spe.getUniqueID();
                String stmS = "SELECT balance FROM eco WHERE uuid = '" + u + "'";
                ResultSet rsS = c.prepareStatement(stmS).executeQuery();
                if (rsS.next()) {
                    //TextUtils.sendChatMessage(spe,new StringTextComponent("您已经开户了。"));
                    result[0] = EBankResult.ALREADY_HAVE_ACCOUNT.get();
                    return;
                }
                String player_name = spe.getDisplayName().getString();
                String stm = "INSERT INTO eco (uuid, player_name, balance) VALUES ('" + u +
                        "', '" + player_name + "', '0.00')";
                System.out.println(stm);
                int rows = c.prepareStatement(stm).executeUpdate();
                result[0] = EBankResult.OK.get();
                //TextUtils.sendChatMessage(spe,new StringTextComponent("开户成功了。"));
            } catch (Exception sqlException) {
                sqlException.printStackTrace();
            }

        }).run();
        return result[0];
    }

    public static void setBalance(ServerPlayerEntity spe, double mny) throws SQLException {
        final double[] money = {mny};
        new Thread(() ->
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection(DB_URL, USR, PWD);
                UUID u = spe.getUniqueID();
                TextUtils.sendChatMessage(spe, new StringTextComponent(spe.getDisplayName().getString() + "," + u));
                String stm = "UPDATE eco SET balance=" + money + ",player_name=\'"
                        + spe.getDisplayName().getString() + "' WHERE uuid='" + u + "\'";
                System.out.println(stm);
                ResultSet rs = c.prepareStatement(stm).executeQuery();
                System.out.println(rs);
                while (rs.next()) {
                    money[0] = rs.getDouble("balance");
                    TextUtils.sendChatMessage(spe, new StringTextComponent("设置" + money[0] + "RDI$"));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static int withdraw(ServerPlayerEntity spe, double moneyToMinus) {
        int[] result = {-1};
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection(DB_URL, USR, PWD);
                UUID u = spe.getUniqueID();
                String stm = "SELECT balance FROM eco WHERE uuid = '" + u + "'";
                System.out.println(stm);
                ResultSet rs = c.prepareStatement(stm).executeQuery();
                if (rs.next()) {
                    double pmoney = rs.getDouble("balance");
                    double newMoney = pmoney - moneyToMinus;
                    if (newMoney < 0) {
                        result[0] = EBankResult.FAIL_NO_ENOUGH_$.get();
                        return;
                    }
                    return;
                }

            } catch (Exception sqlException) {
                sqlException.printStackTrace();
            }

        }).start();
        return result[0];
    }

    public static void deposit(ServerPlayerEntity spe, double money) {

    }

    public static double balance(ServerPlayerEntity spe) throws SQLException, ClassNotFoundException {
        final double[] money = {-1};
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection(DB_URL, USR, PWD);
                UUID u = spe.getUniqueID();
                String stm = "SELECT balance FROM eco WHERE uuid = '" + u + "'";
                System.out.println(stm);
                ResultSet rs = c.prepareStatement(stm).executeQuery();
                while (rs.next()) {
                    money[0] = rs.getDouble("balance");
                    return;
                }
            } catch (Exception sqlException) {
                sqlException.printStackTrace();
            }

        }).run();
        return money[0];
    }
}
