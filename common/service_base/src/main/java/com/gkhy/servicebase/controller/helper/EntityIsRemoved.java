package com.gkhy.servicebase.controller.helper;

import lombok.Value;

// used for remove operation
@Value
public class EntityIsRemoved {
    public boolean enabled;
    public EntityIsRemoved() {
        this.enabled = false;
    }
}
