package com.gkhy.servicebase.controller.helper;

import lombok.Value;

// used for remove operation
@Value
public class EntityIsEnabled {
    public boolean enabled;
    public EntityIsEnabled() {
        this.enabled = false;
    }
}
