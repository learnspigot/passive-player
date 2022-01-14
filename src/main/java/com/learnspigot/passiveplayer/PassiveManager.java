package com.learnspigot.passiveplayer;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PassiveManager {

    private final Map<UUID, PvPData> dataMap = new HashMap<>();

    public void addUUID(final UUID uuid) {
        dataMap.put(uuid, new PvPData());
    }

    public void removeUUID(final UUID uuid) {
        dataMap.remove(uuid);
    }

    public PvPData getPvPData(final UUID uuid) {
        return dataMap.get(uuid);
    }

    public boolean hasPvPEnabled(@NotNull final UUID uuid) {
        return dataMap.get(uuid).isPvpEnabled();
    }

    /*
    public boolean hasPvPEnabled(@NotNull final UUID... uuids) {
        for(final UUID uuid : uuids) {
            if(hasPvPEnabled(uuid)) {
                return true;
            }
        }

        return false;

    }
     */

    public boolean hasPvPDisabled(@NotNull final Entity... entities) {
        for(final Entity en : entities) {
            if(!hasPvPEnabled(en.getUniqueId())) {
                return true;
            }
        }
        return false;

    }

}
