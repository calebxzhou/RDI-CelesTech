package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.model.IslandLocation;
import cn.davickk.rdi.essentials.general.model.Location;
import cn.davickk.rdi.essentials.general.thread.ui.LoadingT;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.arguments.ItemArgument;
import net.minecraft.command.arguments.ItemInput;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class PlayerUtils {
    public static final int LOWEST_LIMIT=0;
    public static final String benefitOfSleepEarly =
            "��˯�ĺô����ٱ�֤˯���������ٽ������Ŷ���������Ȧ�����˵ڶ��쾫����ʢ��" +
                    "�ڿ������أ���ֹ���ֹ��ͷʡ�" +
                    "�ۻ��⽹�ǡ��������������飬ʹ�˳����Ҹ��С�";
    public static final String slogan[] = {
            "�����ܻᷢ�����¸�ת�䡣",
            "�𿴽����ֵû�  С�İ��������嵥  ����",
            //"Ը���׶�����ᣬԸ���ٵ��ѹ���",
            "������ƽ����Ҳ�������档",
            "�������ʣ����¿ɰ���",
            //"�����¿��������  ������ȫ���Ǳ�ͽ��      (���������",
            //"Ψ���Ȱ����ܵ��������¡�",
            "�������޵�ƽ�����ӣ�Ҳ�����⡣",
            //"�������ã����㻷����ۡ�",
            "������̹����  ������һ��һ���������죡   �����������",
            "ȥ��Զ�ĵط����������Ĺ⡣",
            "ÿ������ľ�Ŷ��Ǵ�ů������",
            "��֮��������������",
            //"���ӳ��£����ò�Զ��",
            "��������ԥ�ˣ�һ��Ҫ����ȭ��",
            "���ķ��򣬸��ʺϷ��衣",
            "ǰ·�ƺƵ��������½Կ��ڴ���",
            "��������Ѻۣ��ǹ�����ĵط���",
            //"���ǵ���;���ǳ��󺣡�",
            "���£�����Щ������һ����ô�ܸ�������أ�",
            "�����  �����ǽ����������Ƶ�  ������һ������ȫϯ",
            //"ÿ���˼��̻𣬶���Ҫ�����",
            "����ԭ�����ƣ������������з硣",
            "��Ȥ���������ĵ������",
            "ִ�������룬�����ڵ��¡�",
            "��ѽ  ��������ս����",
            //"������������彫����",
            "С�����ǿ����첻�µز��°�",
            //"�ȷ���������׷��ȥ��",
            "�ã����о���",
            "�ߺ� �� �߰������������������������������������� �߰�����������������",
            "ţ�֣�����"
    };//23 Lines(0~22)

    public static void sayHello(ServerPlayerEntity player) {
        LocalDateTime dateTime = LocalDateTime.now();
        /*DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy��MM��dd�� E HH:mm:ss");
        String formattedDateTime = dateTime.format(myFormatObj).replace("Mon", "����һ")
                .replace("Tue", "���ڶ�").replace("Wed", "������")
                .replace("Thu", "������").replace("Fri", "������")
                .replace("Sat", "������").replace("Sun", "������");*/
        int hour = dateTime.getHour();
        int slog= slogan.length;
        String charTime = "";
        String wishToSend = PlayerUtils.slogan[RandomUtils.generateRandomInt(0, slog-1)];
        TextUtils.sendActionMessage(player, "��e��o" + wishToSend);
        if (hour >= 0 && hour <= 5) {
            charTime = "�賿";
            TextUtils.sendChatMessage(player, new StringTextComponent(PlayerUtils.benefitOfSleepEarly));
        }
        if (hour >= 6 && hour <= 8)
            charTime = "����";
        if (hour >= 9 && hour <= 10)
            charTime = "����";
        if (hour == 11 || hour == 12)
            charTime = "����";
        if (hour >= 13 && hour <= 17)
            charTime = "����";
        if (hour >= 18 && hour <= 23)
            charTime = "����";
        //TextUtils.sendChatMessage(player,new StringTextComponent(PlayerUtils.benefitOfSleepEarly));
        //���Ϻã�XXX��������yyyy��MM��DD��
        TextUtils.sendChatMessage(player,
                charTime + "�� " + player.getDisplayName().getString() + ",��ӭ�ص�RDI��");
    }
    public static String getUUID(ServerPlayerEntity player)
    {
        return player.getUUID().toString();
    }

    public static void doSuicide(ServerPlayerEntity player) {
        player.kill();
    }

    public static boolean minusXPLvl(PlayerEntity player,int lvl2Minus){
        if(hasEnoughXPLvl(player,lvl2Minus))
        {
            player.giveExperienceLevels(-lvl2Minus);
            return true;
        }else return false;
    }
    public static boolean hasEnoughXPLvl(PlayerEntity player,int lvl){
        return player.experienceLevel >= lvl;
    }
    public static void sendLoading(ServerPlayerEntity player) {
        ServerUtils.startThread(new LoadingT(player,1500));
    }
    /*public static List<PlayerEntity> getNearbyPlayersInRange(ServerPlayerEntity player,int range){
        Vector3d pos=player.getPositionVec();
        Vector3d pos1=new Vector3d(pos.x-range, pos.y-range, pos.z-range);
        Vector3d pos2=new Vector3d(pos.x+range,pos.y+range,pos.z+range);
        AxisAlignedBB boundBox=new AxisAlignedBB(pos1,pos2);
        return player.getCommandSenderWorld().getEntitiesWithinAABB(EntityType.PLAYER,boundBox, PlayerEntity::isUser);
    }*/
    public static void randomTeleport(ServerPlayerEntity player,boolean glass){
        int x=RandomUtils.generateRandomInt(-9999,9999);
        int z=RandomUtils.generateRandomInt(-9999,9999);
        Location isl=new Location(x+0.5,220,z+0.5,0,0,"minecraft:overworld");
        teleportPlayer(player,isl);
        if(glass)
            player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(),
                    "fill "+x+" 219 "+z+" "+x+" 219 "+z+" minecraft:glass");

    }
    public static void teleportPlayer(PlayerEntity player, IslandLocation loca){
        teleportPlayer(player,new ResourceLocation("minecraft:overworld"),
                loca.x,loca.y,loca.z,0f,0f);
        //player.changeDimension(player.getServer().getWorld(ServerWorld.OVERWORLD));
        //player.teleportKeepLoaded(loca.x,loca.y,loca.z);
       /* MinecraftServer server=player.getServer();
        String cmd="tp %player %x %y %z"
                .replace("%player",player.getDisplayName().getString())
                .replace("%x", String.valueOf(loca.x))
                .replace("%y", String.valueOf(loca.y))
                .replace("%z", String.valueOf(loca.z));
        //System.out.println(cmd);
        server.getCommands().performCommand(server.createCommandSourceStack(),cmd);*/
    }
    public static void teleportPlayer(PlayerEntity player, BlockPos loca){
        teleportPlayer(player,new ResourceLocation("minecraft:overworld"),
                loca.getX(),loca.getY(),loca.getZ(),0f,0f);
        //player.changeDimension(player.getServer().getWorld(ServerWorld.OVERWORLD));
        //player.teleportKeepLoaded(loca.x,loca.y,loca.z);
       /* MinecraftServer server=player.getServer();
        String cmd="tp %player %x %y %z"
                .replace("%player",player.getDisplayName().getString())
                .replace("%x", String.valueOf(loca.x))
                .replace("%y", String.valueOf(loca.y))
                .replace("%z", String.valueOf(loca.z));
        //System.out.println(cmd);
        server.getCommands().performCommand(server.createCommandSourceStack(),cmd);*/
    }
    public static void teleportPlayer(PlayerEntity player, Location loca){
        teleportPlayer(player,loca.getDims(),loca.getX(),loca.getY(),loca.getZ(),loca.getYaw(),loca.getPitch());
    }
    public static void teleportPlayer(PlayerEntity player, ResourceLocation world, double x, double y, double z, float w, float p){
        MinecraftServer server=player.getServer();
        if(server==null) return;
        String cmd="execute as %player in %world rotated %yaw %pitch run tp %x %y %z"
                .replace("%player",player.getDisplayName().getString())
                .replace("%world", world.toString())
                .replace("%yaw", String.valueOf(w))
                .replace("%pitch", String.valueOf(p))
                .replace("%x", String.valueOf(x))
                .replace("%y", String.valueOf(y))
                .replace("%z", String.valueOf(z));
        //System.out.println(cmd);
        server.getCommands().performCommand(server.createCommandSourceStack(),cmd);
    }
    public static boolean givePlayerItem(ServerPlayerEntity player, String id, int amount) throws CommandSyntaxException {
        ItemInput ia=ItemArgument.item().parse(new StringReader(id));
        /*MinecraftServer server=player.getServer();
        String cmd="give %player %id %amount".replace("%player",player.getDisplayName().getString())
                .replace("%id",id).replace("%amount",amount+"");
        System.out.println(cmd);
        server.getCommands().performCommand(server.createCommandSourceStack(),cmd);*/
        ItemStack stack=ia.createItemStack(amount,false);
        return player.inventory.add(stack);
    }
    public static boolean hasInventorySpace(ServerPlayerEntity player){
        int stack=player.inventory.getFreeSlot();
        if(stack==-1)
            return false;
        else
            return true;
    }
    public static BlockPos lookingAtBlock(PlayerEntity player, boolean isFluid){
        RayTraceResult rays=player.pick(6,1.0f,isFluid);
        Vector3d lookat=(Vector3d) rays.hitInfo;
        return new BlockPos(lookat);
    }
    public static char facing(ServerPlayerEntity player){
        Location loca=new Location(player);
//45~135 ��west 135~180~ -135 ��North  -135~-45 E  -45~0~45 S
        float yaw=loca.getYaw();
        if(yaw>=45 && yaw<=135)
            return '��';
        else if(yaw>135 || yaw<=-135)
            return '��';
        else if(yaw>-135 && yaw<=-45)
            return '��';
        else if(yaw>-45 && yaw<45)
            return '��';
        else return '?';
    }
    public static int getEmptySlotsInventory(ServerPlayerEntity player){
        PlayerInventory inv=player.inventory;
        int emptySlots=0;
        for(int i=0; i<inv.items.size();++i) {
            if (inv.items.get(i).isEmpty()) {
                ++emptySlots;
            }
        }
        return emptySlots;
    }
    public static void givePlayerRandomOre(ServerPlayerEntity player){

    }


}
