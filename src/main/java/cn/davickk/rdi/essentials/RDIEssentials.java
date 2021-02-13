package cn.davickk.rdi.essentials;

import cn.davickk.rdi.essentials.general.register.ModCommands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

@Mod(RDIEssentials.MODID)
public class RDIEssentials {
    public static final String MODID = "rdi-essentials";
    public static final String VERSION="1.8";
    public static Connection SQL_CONN;
    public RDIEssentials() throws SQLException, ClassNotFoundException {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        RDIEssentials.createSQLConnection();
        //modEventBus.addListener(this::onCommonSetup);
        //modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC, MODID + ".toml");
        // Mark rdi-essentials as only required on the server
        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        MinecraftForge.EVENT_BUS.register(this);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static void createSQLConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            SQL_CONN= DriverManager.getConnection(DB_URL, USR, PWD);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }
    /*
        private void onCommonSetup(final FMLCommonSetupEvent event) {
            PROXY.init();
            Networking.setup();
            DataLoader.setupMain(event);
        }

        //@SubscribeEvent
        public void onServerStarting(FMLServerStartingEvent event) {
            DataLoader.setupWorld(event);
            DataLoader.load();
        }
    */
    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event) {
        ModCommands.registerCommands(event);
    }





}
