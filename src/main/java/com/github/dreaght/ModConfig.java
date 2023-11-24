package com.github.dreaght;

public class ModConfig {
    private static ModConfig instance;
    private boolean modEnabled;

    private ModConfig(boolean modEnabled) {
        this.modEnabled = modEnabled;
    }

    public static ModConfig getInstance() {
        if (instance == null) {
            instance = new ModConfig(true);
        }
        return instance;
    }

    public boolean isModEnabled() {
        return modEnabled;
    }

    public void setModEnabled(boolean modEnabled) {
        this.modEnabled = modEnabled;
    }
}
