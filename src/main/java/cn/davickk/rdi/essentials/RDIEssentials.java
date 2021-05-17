package cn.davickk.rdi.essentials;

import cn.davickk.rdi.essentials.general.register.ModCommands;
import cn.davickk.rdi.essentials.general.util.SQLUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLException;

@Mod(RDIEssentials.MODID)
public class RDIEssentials {
    public static final String MODID = "rdi-essentials";
    public static final String VERSION="2.0pre1";


    public RDIEssentials() throws SQLException, ClassNotFoundException {
        getSQLUtils();
                //modEventBus.addListener(this::onCommonSetup);
        //modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC, MODID + ".toml");
        // Mark rdi-essentials as only required on the server
        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        MinecraftForge.EVENT_BUS.register(this);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }
    public static SQLUtils getSQLUtils(){
        return new SQLUtils();
    }
        @SubscribeEvent
        public void onServerStarting(FMLServerStartingEvent event) {
            //sqlu=new SQLUtils();
        }

    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event) {
        ModCommands.registerCommands(event);
    }





}
