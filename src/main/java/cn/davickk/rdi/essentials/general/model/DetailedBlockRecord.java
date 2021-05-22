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
        TIME_ASC("<�Ӿɼ�¼��ʼ>"),//ʱ�����򣨴���ɵļ�¼��ʼ��
        TIME_DESC("<���¼�¼��ʼ>");//ʱ�併�򣨴����µļ�¼��ʼ��(Ĭ��) ORDER BY opr_time DESC
        private final String str;
        SortType(String str){
            this.str=str;
        }
        public String getStr(){
            return str;
        }
    }
    public enum FilterType{
        PLAYER_NAME("<���������>"),//���������ɸѡ��ֻ��ĳ��ҵģ�
        PLAYER_NAME_EXCLUDE("<�ų�ĳ�����>"),//���������ɸѡ���ų�ĳ��ҵ�
        PLAYER_NAME_LIST("<��������б����>"),//��������б�ɸѡ eg xxx,uuu,zzz,yyy....(���ŷָ�)

        //����
        PLACE_ONLY("<������>"),//���鿴���ò���
        DESTROY_ONLY("<���ƻ�>"),//���鿴�ƻ�����

        MOD_ITEM("<��ĳMod����Ʒ>"),//����mod��Ʒɸѡ ��mod id��

        DATE_SCOPE("<���ڷ�Χ>"),//�������ڷ�Χɸѡ yyyyMMdd-yyyyMMdd
        DATETIME_SCOPE("<����+ʱ�䷶Χ>"),//��������+ʱ�䷶Χɸѡ yyyyMMdd.HHmmSS-

        XYZ_SCOPE("<���귶Χ>") ;// �������귶Χɸѡ
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
