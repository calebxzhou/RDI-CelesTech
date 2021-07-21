package cn.davickk.rdi.essentials.general.command.impl.blockrec;

import cn.davickk.rdi.essentials.general.command.BaseCommand;

import java.util.ArrayList;
import java.util.List;

public class RecFilterCmd extends BaseCommand {
    private List<String> oprList=new ArrayList<>();
    public RecFilterCmd(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
        oprList.add("time_asc");
        oprList.add("time_desc");
        oprList.add("player_name");
        oprList.add("player_name_exclude");
    }
    // /recfilter <type>
}
