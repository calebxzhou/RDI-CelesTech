package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.SQLUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.sql.*;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class FromOldHomeThread extends Thread {
    private ServerPlayerEntity player;

    public FromOldHomeThread(ServerPlayerEntity player) {
        this.player = player;
    }

    public void run() {
        try {
            /*HOME 1.0
            String dic = System.getProperty("user.dir") + "/" + RDIEssentials.MODID
                    + "/homes/" + player.getUniqueID() + ".json";
            BufferedReader reader = new BufferedReader(new FileReader(dic));
            String line = reader.readLine();
            double x = 0, y = 0, z = 0;
            for (int i = 1; i < 9 && line != null; ++i) {

                if (i == 4) {
                    System.out.println(line);
                    String[] splited = line.replace(",", "")
                            .replace(" ", "")
                            .split(":");
                    x = Double.parseDouble(splited[1]);
                }
                if (i == 5) {
                    System.out.println(line);
                    String[] splited = line.replace(",", "")
                            .replace(" ", "")
                            .split(":");
                    y = Double.parseDouble(splited[1]);
                }
                if (i == 6) {
                    System.out.println(line);
                    String[] splited = line.replace(",", "")
                            .replace(" ", "")
                            .split(":");
                    z = Double.parseDouble(splited[1]);
                }
                line = reader.readLine();
            }
            reader.close();


            float p = 0f;
            float w = 0f;*/
            String playerName = player.getDisplayName().getString();
            String u= PlayerUtils.getLegacyUUID(player);
            String uuid=PlayerUtils.getUUID(player);
            int serverPort = player.getServer().getServerPort();
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(homeurl + serverPort + "?useSSL=true", USR, PWD);
            /*DatabaseMetaData dbm = c.getMetaData();
            ResultSet rs = dbm.getTables(null, "rdihome" + serverPort, u, null);
            if (!rs.next()) {
                String stm1 = "CREATE TABLE " + u + " (homeName tinytext, x double, y double, z double, " +
                        "p float, w float, activ bool) COMMENT '" + playerName + "';";
                System.out.println(stm1);
                c.prepareStatement(stm1).executeUpdate();
            }
            rs.close();
            ResultSet rs2 = c.prepareStatement("SELECT COUNT(*) AS rowcount FROM " + u).executeQuery();
            rs2.next();
            int homeCount = rs2.getInt("rowcount");
            TextUtils.sendChatMessage(player, "您已经设置过 " + homeCount + " 个家了。");
            if (homeCount > SethomeThread.maxHomes) {
                //toRet[0]= EHomeResult.HOME_REACHED_MAX_AMOUNT.get();
                TextUtils.sendChatMessage(player, "最多只能设置" + SethomeThread.maxHomes + "个家");
                c.close();
                return;
            }
            rs2.close();*/
            DatabaseMetaData dbm = c.getMetaData();
            ResultSet rs = dbm.getTables(null, "rdihome" + serverPort, u, null);
            if (!rs.next()) {
                TextUtils.sendChatMessage(player,"没有数据可导入");
                return;
            }
            rs.close();
            ResultSet rs3 = c.prepareStatement("SELECT * FROM " + u ).executeQuery();
            //ServerWorld world = player.getServerWorld();
            String homeName= null;
            double x = 0;
            double y = 0;
            double z = 0;
            float w = 0;
            float p = 0;
            Connection c2 = DriverManager.getConnection(DB_URL, USR, PWD);
            String table=SQLUtils.getHomeTable(serverPort);
            while (rs3.next()) {
                homeName = rs3.getString("homeName");
                x = rs3.getDouble("x");
                y = rs3.getDouble("y");
                z = rs3.getDouble("z");
                w = rs3.getFloat("w");
                p = rs3.getFloat("p");
                boolean isActive = rs3.getBoolean("activ");
                TextUtils.sendChatMessage(player,"导入"+homeName+":"+(int)x+","+(int)y+","+(int)z+","+isActive);
                String st = "INSERT INTO " + table + " (uuid, playerName, homeName, dims, x, y, z, w, p, activ) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = c2.prepareStatement(st);
                stmt.setString(1, uuid);
                stmt.setString(2, playerName);
                stmt.setString(3, homeName);
                stmt.setString(4, "minecraft:overworld");
                stmt.setDouble(5, x);
                stmt.setDouble(6, y);
                stmt.setDouble(7, z);
                stmt.setDouble(8, w);
                stmt.setDouble(9, p);
                stmt.setBoolean(10, isActive);
                stmt.executeUpdate();

                TextUtils.sendChatMessage(player, "成功导入家" + homeName + "，位置在(" + (int) x + ", " + (int) y + ", " + (int) z + ")");

            }
            c.prepareStatement("DROP TABLE "+u).executeUpdate();
            c.close();
            c2.close();
            /*
            if(world.getDimensionType().equals(DimensionType.THE_NETHER)
                    || world.getDimensionType().equals(DimensionType.THE_END))
            {
                TextUtils.sendChatMessage(player,"必须回到主世界才能执行本指令");
                return;
            }*/
            /*
            if (!isActive) {
                TextUtils.sendChatMessage(player, homeName + "还没有激活，因此无法传送。");
                TextUtils.clickableContent2Send(player, EHomeText.ACTIVATE.text.replace("%s",homeName),
                        EHomeText.ACTIVATE.cmd.replace("%s",homeName));
                return;
            }*///toRet[0]= EHomeResult.OK.get();
        } catch (Exception e) {
            throw new IllegalArgumentException("找不到数据");
        }
    }
}
