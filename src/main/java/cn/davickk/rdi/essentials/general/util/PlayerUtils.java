package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EWorld;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.thread.ui.LoadingT;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.arguments.ItemArgument;
import net.minecraft.command.arguments.ItemInput;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.core.jmx.Server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class PlayerUtils {
    public static final int LOWEST_LIMIT=30;
    public static final String benefitOfSleepEarly =
            "早睡的好处：①保证睡眠质量，促进肝脏排毒，告别黑眼圈，令人第二天精力旺盛。" +
                    "②控制体重，防止出现过劳肥。" +
                    "③缓解焦虑、抑郁，改善心情，使人充满幸福感。";
    public static final String slogan[] = {
            "好事总会发生在下个转弯。",
            "别看今天闹得欢  小心啊将来拉清单  （雾",
            //"愿世俗多点温柔，愿你少点难过。",
            "生活再平凡，也是限量版。",
            "生活明朗，万事可爱。",
            //"不惧怕空输的威力  这明显全都是暴徒！      (名场面放送",
            //"唯有热爱，能抵漫长岁月。",
            "吹不出褶的平静日子，也在闪光。",
            //"世间美好，和你环环相扣。",
            "看我派坦克来  把你们一个一个都送上天！   （名场面放送",
            "去更远的地方，见更亮的光。",
            "每个冬天的句号都是春暖花开。",
            "心之所向，素履以往。",
            //"日子常新，美好不远。",
            "不能再犹豫了，一定要出重拳！",
            "逆风的方向，更适合飞翔。",
            "前路浩浩荡荡，万事皆可期待。",
            "万物皆有裂痕，是光进来的地方。",
            //"我们的征途是星辰大海。",
            "和这些虫豸在一起  怎么能搞好××呢？         （名场面放送",
            "结果呢  还不是进了西冰库大酒店  吃了这一顿满汉全席",
            //"每颗人间烟火，都不要错过。",
            "生活原本沉闷，但跑起来就有风。",
            "有趣都藏在无聊的日子里。",
            "执着于理想，纯粹于当下。",
            "哎呀  那声音是战车吧",
            //"不念过往，不惧将来。",
            "小孩子们可是天不怕地不怕啊",
            //"等风来，不如追风去。",
            "好，很有精神！",
            "哼哼 啊 哼啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 哼啊啊啊啊啊啊啊啊啊",
            "牛字，嗯了"
    };//23 Lines(0~22)

    public static void sayHello(ServerPlayerEntity player) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy年MM月dd日 E HH:mm:ss");
        String formattedDateTime = dateTime.format(myFormatObj).replace("Mon", "星期一")
                .replace("Tue", "星期二").replace("Wed", "星期三")
                .replace("Thu", "星期四").replace("Fri", "星期五")
                .replace("Sat", "星期六").replace("Sun", "星期日");
        int hour = dateTime.getHour();
        int slog= slogan.length;
        String charTime = "";
        String wishToSend = PlayerUtils.slogan[RandomUtils.generateRandomInt(0, slog-1)];
        TextUtils.sendChatMessage(player, "§e§o" + wishToSend);
        if (hour >= 0 && hour <= 5) {
            charTime = "凌晨";
            TextUtils.sendChatMessage(player, new StringTextComponent(PlayerUtils.benefitOfSleepEarly));
        }
        if (hour >= 6 && hour <= 8)
            charTime = "早上";
        if (hour >= 9 && hour <= 10)
            charTime = "上午";
        if (hour == 11 || hour == 12)
            charTime = "中午";
        if (hour >= 13 && hour <= 17)
            charTime = "下午";
        if (hour >= 18 && hour <= 23)
            charTime = "晚上";
        //TextUtils.sendChatMessage(player,new StringTextComponent(PlayerUtils.benefitOfSleepEarly));
        //早上好，XXX，现在是yyyy年MM月DD日
        TextUtils.sendChatMessage(player,
                charTime + "好，" + player.getDisplayName().getString() + "，现在是 " + formattedDateTime + " (UTC+8)");
        }
    public static String getUUID(ServerPlayerEntity player)
    {
        return player.getUniqueID().toString();
    }
    @Deprecated
    public static String getLegacyUUID(ServerPlayerEntity player){
        String u1=player.getUniqueID().toString();
        String u=u1.replace("-","");
        if(u.startsWith("226e"))
            return u.replace("226e","22ee");
        else
            return u;
    }
    public static void doSuicide(ServerPlayerEntity player) {
        player.onKillCommand();
    }

    public static boolean minusXPLvl(PlayerEntity player,int lvl2Minus){
        if(hasEnoughXPLvl(player,lvl2Minus))
        {
            player.addExperienceLevel(-lvl2Minus);
            return true;
        }else return false;
    }
    public static boolean hasEnoughXPLvl(PlayerEntity player,int lvl){
        return player.experienceLevel >= lvl;
    }
    public static void sendLoading(ServerPlayerEntity player) {
        ServerUtils.startThread(new LoadingT(player,1500));
    }
    public static List<PlayerEntity> getNearbyPlayersInRange(ServerPlayerEntity player,int range){
        Vector3d pos=player.getPositionVec();
        Vector3d pos1=new Vector3d(pos.x-range, pos.y-range, pos.z-range);
        Vector3d pos2=new Vector3d(pos.x+range,pos.y+range,pos.z+range);
        AxisAlignedBB boundBox=new AxisAlignedBB(pos1,pos2);
        return player.getEntityWorld().getEntitiesWithinAABB(EntityType.PLAYER,boundBox, PlayerEntity::isUser);
    }
    public static boolean isInCreateServ(ServerPlayerEntity player){
        return player.getServer().getServerPort()==ServerUtils.CRET_PORT;
    }
    public static void randomTeleport(ServerPlayerEntity player,boolean glass){
        int x=RandomUtils.generateRandomInt(-4900,4900);
        int z=RandomUtils.generateRandomInt(-4900,4900);
        IslandLocation isl=new IslandLocation
                (x,220,z);
        teleportPlayer(player,isl);
        if(glass)
            player.getServer().getCommandManager().handleCommand(player.getServer().getCommandSource(),
                    "fill "+x+" 219 "+z+" "+x+" 219 "+z+" minecraft:glass");

    }
    public static void forceBack2Spawn(PlayerEntity player){

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
        server.getCommandManager().handleCommand(server.getCommandSource(),cmd);*/
    }
    public static void teleportPlayer(PlayerEntity player, Location loca){
        teleportPlayer(player,loca.dims,loca.x,loca.y,loca.z,loca.rotationYaw,loca.rotationPitch);
    }
    public static void teleportPlayer(PlayerEntity player, ResourceLocation world, double x, double y, double z, float w, float p){
        MinecraftServer server=player.getServer();
        String cmd="execute as %player in %world rotated %yaw %pitch run tp %x %y %z"
                .replace("%player",player.getDisplayName().getString())
                .replace("%world", world.toString())
                .replace("%yaw", String.valueOf(w))
                .replace("%pitch", String.valueOf(p))
                .replace("%x", String.valueOf(x))
                .replace("%y", String.valueOf(y))
                .replace("%z", String.valueOf(z));
        //System.out.println(cmd);
        server.getCommandManager().handleCommand(server.getCommandSource(),cmd);
    }
    public static boolean givePlayerItem(ServerPlayerEntity player, String id, int amount) throws CommandSyntaxException {
        ItemInput ia=ItemArgument.item().parse(new StringReader(id));
        /*MinecraftServer server=player.getServer();
        String cmd="give %player %id %amount".replace("%player",player.getDisplayName().getString())
                .replace("%id",id).replace("%amount",amount+"");
        System.out.println(cmd);
        server.getCommandManager().handleCommand(server.getCommandSource(),cmd);*/
        ItemStack stack=ia.createStack(amount,false);
        return player.inventory.addItemStackToInventory(stack);
    }
    public static boolean hasInventorySpace(ServerPlayerEntity player){
        int stack=player.inventory.getFirstEmptyStack();
        if(stack==-1)
            return false;
        else
            return true;
    }
    public static BlockPos lookingAtBlock(ServerPlayerEntity player, boolean isFluid){
        RayTraceResult rays=player.pick(6,1.0f,isFluid);
        Vector3d lookat=rays.getHitVec();
        return new BlockPos(lookat);
    }
    public static char facing(ServerPlayerEntity player){
        Location loca=new Location(player);
//45~135 西west 135~180~ -135 北North  -135~-45 E  -45~0~45 S
        float yaw=loca.rotationYaw;
        if(yaw>=45 && yaw<=135)
            return '西';
        else if(yaw>135 || yaw<=-135)
            return '北';
        else if(yaw>-135 && yaw<=-45)
            return '东';
        else if(yaw>-45 && yaw<45)
            return '南';
        else return '?';
    }
    public static int getEmptySlotsInventory(ServerPlayerEntity player){
        PlayerInventory inv=player.inventory;
        int emptySlots=0;
        for(int i=0; i<inv.mainInventory.size();++i) {
            if (inv.mainInventory.get(i).isEmpty()) {
                ++emptySlots;
            }
        }
        return emptySlots;
    }
    public static void givePlayerRandomOre(ServerPlayerEntity player){

    }


}
