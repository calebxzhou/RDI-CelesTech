package cn.davickk.rdi.essentials.general.model;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerTpaRequest {


    public PlayerEntity getFromPlayer() {
        return fromPlayer;
    }

    public PlayerTpaRequest setFromPlayer(PlayerEntity fromPlayer) {
        this.fromPlayer = fromPlayer;
        return this;
    }

    public PlayerEntity getToPlayer() {
        return toPlayer;
    }

    public PlayerTpaRequest setToPlayer(PlayerEntity toPlayer) {
        this.toPlayer = toPlayer;
        return this;
    }

    public PlayerTpaRequest(PlayerEntity fromPlayer, PlayerEntity toPlayer) {

        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
    }


    private PlayerEntity fromPlayer;
    private PlayerEntity toPlayer;
}
