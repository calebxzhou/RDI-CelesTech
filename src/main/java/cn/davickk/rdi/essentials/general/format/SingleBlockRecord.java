package cn.davickk.rdi.essentials.general.format;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "block_rec")
public class SingleBlockRecord {
    private String record_id,playerName,blockType,blockNbt;
    private int oprType; // 1=place 2=brake 3=take out 4=put in
    private String dimension;
    private double posX,posY, posZ;
    private Timestamp oprTime;

    public final int PLACE=1,BRAKE=2,TAKE=3,PUT=4;

    public SingleBlockRecord(String record_id, String playerName, String blockType, String blockNbt, int oprType, String dimension, double posX, double posY, double poxZ, Timestamp oprTime) {
        this.record_id = record_id;
        this.playerName = playerName;
        this.blockType = blockType;
        this.blockNbt = blockNbt;
        this.oprType = oprType;
        this.dimension = dimension;
        this.posX = posX;
        this.posY = posY;
        this.posZ = poxZ;
        this.oprTime = oprTime;
    }

    public SingleBlockRecord() {

    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "record_id", length = 36)
    public String getRecord_id() {
        return record_id;
    }
    @Column(name = "oprTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Timestamp getOprTime() {
        return oprTime;
    }

    public SingleBlockRecord setRecord_id(String record_id) {
        this.record_id = record_id;
        return this;
    }
    @Column(name = "playerName",nullable=false)
    public String getPlayerName() {
        return playerName;
    }

    public SingleBlockRecord setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    @Column(name="blockType",nullable=false)
    public String getBlockType() {
        return blockType;
    }

    public SingleBlockRecord setBlockType(String blockType) {
        this.blockType = blockType;
        return this;
    }
    @Column(name="blockNbt",nullable=false)
    public String getBlockNbt() {
        return blockNbt;
    }

    public SingleBlockRecord setBlockNbt(String blockNbt) {
        this.blockNbt = blockNbt;
        return this;
    }
    @Column(name="oprType",nullable=false)
    public int getOprType() {
        return oprType;
    }

    public SingleBlockRecord setOprType(int oprType) {
        this.oprType = oprType;
        return this;
    }
    @Column(name="dimension",nullable=false)
    public String getDimension() {
        return dimension;
    }

    public SingleBlockRecord setDimension(String dimension) {
        this.dimension = dimension;
        return this;
    }
    @Column(name="posX",nullable=false)
    public double getPosX() {
        return posX;
    }

    public SingleBlockRecord setPosX(double posX) {
        this.posX = posX;
        return this;
    }
    @Column(name="posY",nullable=false)
    public double getPosY() {
        return posY;
    }

    public SingleBlockRecord setPosY(double posY) {
        this.posY = posY;
        return this;
    }
    @Column(name="posZ",nullable=false)
    public double getPosZ() {
        return posZ;
    }

    public SingleBlockRecord setPosZ(double poxZ) {
        this.posZ = poxZ;
        return this;
    }

    public SingleBlockRecord setOprTime(Timestamp oprTime) {
        this.oprTime = oprTime;
        return this;
    }
}
