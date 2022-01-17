package com.learnspigot.passiveplayer;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class PvPData {

    @Getter
    private boolean pvpEnabled;
    private long lastToggle;

    @SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
    public boolean togglePvP() {
        lastToggle = System.currentTimeMillis();
        return pvpEnabled = !pvpEnabled;
    }

    public int secondSinceLastToggle() {
        return (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-lastToggle);
    }

}
