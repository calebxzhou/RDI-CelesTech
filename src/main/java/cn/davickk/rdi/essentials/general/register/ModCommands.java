package cn.davickk.rdi.essentials.general.register;

import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.command.impl.blockrec.BlockRecordCmd;
import cn.davickk.rdi.essentials.general.command.impl.cloudinv.RinvCommand;
import cn.davickk.rdi.essentials.general.command.impl.home.*;
import cn.davickk.rdi.essentials.general.command.impl.island.*;
import cn.davickk.rdi.essentials.general.command.impl.player.*;
import cn.davickk.rdi.essentials.general.command.impl.rdi.RdiCommand;
import cn.davickk.rdi.essentials.general.command.impl.spawn.SpawnCommand;
import cn.davickk.rdi.essentials.general.command.impl.teleport.TpYesCommand;
import cn.davickk.rdi.essentials.general.command.impl.teleport.TpaCommand;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;

public final class ModCommands {

    private static final ArrayList<BaseCommand> commands = new ArrayList<>();

    public static void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();

        // Ambient
        // Time
/*
        commands.add(new DayCommand("day", 2, ModConfig.time_enable));
        commands.add(new NightCommand("night", 2, ModConfig.time_enable));

        // Weather

        commands.add(new RainCommand("rain", 2, ModConfig.weather_enable));
        commands.add(new SunCommand("sun", 2, ModConfig.weather_enable));
        commands.add(new ThunderCommand("thunder", 2, ModConfig.weather_enable));
*/      //commands.add(new ReconnSQLCmd("reconnsql",4,true));
        // Spawn

        commands.add(new SpawnCommand("spawn", 0, true));
        //commands.add(new SetSpawnCommand("setspawn", 2, ModConfig.spawn_enable));
        //Island
        commands.add(new IslandCommand("rkd",0,true));
        commands.add(new CreateIslandCmd("createkd",0,true));
        commands.add(new CreateIslandHereCmd("createkdhere",0,true));
        commands.add(new DeleteIslandCmd("shanchukongdao",0,true));
        // Player
      //  commands.add(new GetFromVoidCmd("getfromvoid",0,true));
        commands.add(new KickMeCommand("kickme",0,true));
        commands.add(new ClearPhantomCmd("clearphantom",0,true));
        commands.add(new RollCommand("rroll",0,true));
//        commands.add(new AfkCommand("afk", 0, true));
        commands.add(new BlockRecordCmd("rbrx",0,true));
        commands.add(new OnHandCommand("onhand", 0, true));
        commands.add(new Obsi2LavaCmd("obsi2lava",0,true));
        commands.add(new Water2IceCmd("water2ice",0,true));
        commands.add(new SummonMetrCmd("ae2yunshi",0,true));
        //commands.add(new RtpsCommand("rfz", 0, true));
        commands.add(new RtpsCommand("tps", 0, true));
        commands.add(new SaveCommand("SAVE", 0, true));
        commands.add(new TpaCommand("tpa",0,true));
        commands.add(new TpYesCommand("tpyes",0,true));
        commands.add(new RdiCommand("rdi",0,true));
        //commands.add(new BackCommand("back",0,true));
        //TODO
        // commands.add(new BackCommand("back", 0, ModConfig.back_enable));
        //commands.add(new RndtpCommand("rndtp", 0, ModConfig.rndtp_enable));
        commands.add(new PlayerSuicideCommand("shalewo8", 0, true));
        //commands.add(new TrashCommand("trash", 0, ModConfig.trash_enable));
        // Economy//TODO
        //commands.add(new MoneyCommand("money",0,true));
        //commands.add(new CreateAccountCommand("kaihu",0,true));
        //commands.add(new SetMoneyCommand("setmoney", 2, true));
        //RDI Cloud Inventory
        commands.add(new RinvCommand("rinv", 0, true));
        /*commands.add(new RinvUpCommand("rinvput", 0, true));
        commands.add(new RinvDownCommand("rinvget", 0, true));
        commands.add(new RinvDelCommand("rinvshanchudelete", 0, true));
        commands.add(new RinvLsCommand("rinvls", 0, true));*/
        // Home

        commands.add(new DelHomeCommand("delhome", 0, true));
        commands.add(new SetHomeCommand("sethome", 0, true));
        commands.add(new HomeActiveCommand("activhome", 0, true));
        commands.add(new HomeCommand("home", 0, true));
        //commands.add(new HomeListCommand("listhome", 0, true));
        commands.add(new ActionsOfHomeCmd("actions4home",0,true));
        //commands.add(new FromOldHomeCommand("fromoldhome", 0, true));
        //commands.add(new ShareHomeCmd("sharehome",0,true));
        commands.add(new UpdateHomeCmd("updatehome",0,true));
        commands.forEach((cmd) -> {
            if (cmd.isEnabled() && cmd.setExecution() != null) {
                dispatcher.register(cmd.getBuilder());
            }
        });
    }

}
