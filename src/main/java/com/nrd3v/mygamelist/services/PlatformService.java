package com.nrd3v.mygamelist.services;

import com.nrd3v.mygamelist.core.CoreService;
import com.nrd3v.mygamelist.entities.Platform;

public class PlatformService extends CoreService {

    public Platform create(Platform platform) {
        if (platform != null) {
            this.createEntity(platform);
        }
        return platform;
    }

    public Platform update(Platform platform) {
        if (platform != null) {
            this.updateEntity(platform);
        }
        return platform;
    }

    public void delete(Platform platform) {
        if (platform != null) {
            this.deleteEntity(platform);
        }
    }
}
