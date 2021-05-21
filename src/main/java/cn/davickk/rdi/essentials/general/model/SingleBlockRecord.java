package cn.davickk.rdi.essentials.general.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleBlockRecord implements Serializable {
    private int record_id;

    private String player_name;

    private String block_type;

    private int oprType; // 1=place 2=brake 3=take out 4=put in

    private String dimension;

    private int posX;

    private int posY;

    private int posZ;

    private Timestamp opr_time;

    public static final int PLACE=1,BRAKE=2,TAKE=3,PUT=4,UNDEFINED=-1;
    public String toString(){
       return new Gson().toJson(this);
    }
}
