package cn.davickk.rdi.essentials.general.model;

public class Rinv {
    public String getUuid() {
        return uuid;
    }

    public Rinv setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getSerializedNbt() {
        return serializedNbt;
    }

    public Rinv setSerializedNbt(String serializedNbt) {
        this.serializedNbt = serializedNbt;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Rinv setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    private String uuid,serializedNbt,playerName;
}
