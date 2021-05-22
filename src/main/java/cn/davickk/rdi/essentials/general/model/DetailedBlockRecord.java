package cn.davickk.rdi.essentials.general.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailedBlockRecord {
    public enum SortType{
        TIME_ASC("<从旧记录开始>"),//时间升序（从最旧的记录开始）
        TIME_DESC("<从新记录开始>");//时间降序（从最新的记录开始）(默认) ORDER BY opr_time DESC
        private final String str;
        SortType(String str){
            this.str=str;
        }
        public String getStr(){
            return str;
        }
    }
    public enum FilterType{
        PLAYER_NAME("<仅单个玩家>"),//按照玩家名筛选（只看某玩家的）
        PLAYER_NAME_EXCLUDE("<排除某个玩家>"),//按照玩家名筛选，排除某玩家的
        PLAYER_NAME_LIST("<按照玩家列表查找>"),//按照玩家列表筛选 eg xxx,uuu,zzz,yyy....(逗号分割)

        //互斥
        PLACE_ONLY("<仅放置>"),//仅查看放置操作
        DESTROY_ONLY("<仅破坏>"),//仅查看破坏操作

        MOD_ITEM("<仅某Mod的物品>"),//按照mod物品筛选 （mod id）

        DATE_SCOPE("<日期范围>"),//按照日期范围筛选 yyyyMMdd-yyyyMMdd
        DATETIME_SCOPE("<日期+时间范围>"),//按照日期+时间范围筛选 yyyyMMdd.HHmmSS-

        XYZ_SCOPE("<坐标范围>") ;// 按照坐标范围筛选
        private final String str;

        FilterType(String str){
            this.str=str;
        }
        public String getStr(){
            return str;
        }
    }
    private int x,y,z;
    private SortType sortType;
    private FilterType filterType;
    public static DetailedBlockRecord fromString(String json){
        return new Gson().fromJson(json,DetailedBlockRecord.class);
    }
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
