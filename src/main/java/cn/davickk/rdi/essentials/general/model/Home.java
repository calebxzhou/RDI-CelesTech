package cn.davickk.rdi.essentials.general.model;

import cn.davickk.rdi.essentials.general.lib.Location;

public class Home {
    public Home(String uuid, String playerName, String homeName, String dims, int port, double x, double y, double z, float w, float p, int activ) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.homeName = homeName;
        this.dims = dims;
        this.port = port;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.p = p;
        this.activ = activ;
    }

    private String uuid;
    private String playerName;
    private String homeName;
    private String dims;



    private String comments;
    private int port;
    private double x,y,z;
    private float w,p;
    private int activ;
    public String getUuid() {
        return uuid;
    }

    public Home setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Home setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public String getHomeName() {
        return homeName;
    }

    public Home setHomeName(String homeName) {
        this.homeName = homeName;
        return this;
    }

    public String getDims() {
        return dims;
    }

    public Home setDims(String dims) {
        this.dims = dims;
        return this;
    }

    public int getPort() {
        return port;
    }

    public Home setPort(int port) {
        this.port = port;
        return this;
    }

    public double getX() {
        return x;
    }

    public Home setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Home setY(double y) {
        this.y = y;
        return this;
    }

    public double getZ() {
        return z;
    }

    public Home setZ(double z) {
        this.z = z;
        return this;
    }

    public double getW() {
        return w;
    }

    public Home setW(float w) {
        this.w = w;
        return this;
    }

    public double getP() {
        return p;
    }

    public Home setP(float p) {
        this.p = p;
        return this;
    }

    public int getActiv() {
        return activ;
    }

    public Home setActiv(int activ) {
        this.activ = activ;
        return this;
    }
    public String getComment() {
        if(comments==null)
            return "";
        else
            return comments;
    }

    public Home setComment(String comments) {
        this.comments = comments;
        return this;
    }

    public Location getLocation(){
        return new Location(x,y,z,w,p,dims);
    }
}
