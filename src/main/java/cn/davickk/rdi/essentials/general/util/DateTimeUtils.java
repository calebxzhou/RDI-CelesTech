package cn.davickk.rdi.essentials.general.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static Timestamp getTimestampNow(){
        java.util.Date date = new Date(System.currentTimeMillis());
        Timestamp param = new java.sql.Timestamp(date.getTime());
        return param;
    }
    public static String getFormattedDateTime(LocalDateTime dateTime){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy��MM��dd�� E HH:mm:ss");
        String formattedDateTime = dateTime.format(myFormatObj).replace("Mon", "��һ")
                .replace("Tue", "�ܶ�").replace("Wed", "����")
                .replace("Thu", "����").replace("Fri", "����")
                .replace("Sat", "����").replace("Sun", "����");
        return formattedDateTime;
    }
    public static String getFormattedDateTime(LocalDateTime dateTime,String pattern){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = dateTime.format(myFormatObj).replace("Mon", "��һ")
                .replace("Tue", "�ܶ�").replace("Wed", "����")
                .replace("Thu", "����").replace("Fri", "����")
                .replace("Sat", "����").replace("Sun", "����");
        return formattedDateTime;
    }
    public static String getWeekByInt(int day){
        switch (day){
            case 1:
                return "һ";
            case 2:
                return "��";
            case 3:
                return "��";
            case 4:
                return "��";
            case 5:
                return "��";
            case 6:
                return "��";
            case 7:
                return "��";
            default:
                return "?";
        }
    }
    /**
     * 1.day��ͬ - ����
     * 2.day��1  - ����
     * 3.day��2~7 - ��123/����456��
     *
     *
     * @param dtRec ������
     * @param dtNow ������--���ڵ�����
     * @return ������ ����9��20������2��40���ܶ�2��49��������22��45��4��4��12��45��20xx��x��x�� xx:xx
     */
    public static String getComparedDateTime(LocalDateTime dtRec,LocalDateTime dtNow){
        String formattedTimePrefix = null;
        //������ǽ��꣬����������¼
        if(!(dtRec.getYear()==dtNow.getYear())){
            return getFormattedDateTime(dtRec);
        }
        //����ǽ��꣬�� ��������£�����X��X�� xx:xx��¼
        if(!(dtRec.getMonth().getValue()==dtNow.getMonth().getValue())){
            return getFormattedDateTime(dtRec,"MM��dd�� HH:mm");
        }

        //����ǽ��꣬�� ����£����������� ǰ�� ����磨������ʾ�ܼ�/���ܼ�
        if(dtNow.getDayOfYear() - dtRec.getDayOfYear()>=2){
            int dt2Week=dtNow.getDayOfWeek().getValue();
            int dt1Week=dtRec.getDayOfWeek().getValue();
            //����X>����X˵��������
            if(dt1Week>dt2Week)
                formattedTimePrefix="����"+getWeekByInt(dt1Week);
            else
                formattedTimePrefix="��"+getWeekByInt(dt2Week);
        }
        //���������
        if(dtRec.getDayOfYear()==dtNow.getDayOfYear()-1){
            formattedTimePrefix="����";
        }
            //����ǽ���
        if(dtRec.getDayOfYear()==dtNow.getDayOfYear()){
            formattedTimePrefix="����";
        }



        return formattedTimePrefix+dtRec.getHour()+":"+dtRec.getMinute();

    }
}
