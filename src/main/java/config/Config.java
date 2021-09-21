package config;

import static org.dreambot.api.methods.MethodProvider.log;

/**
 * singleton class to hold global settings
 */

public class Config {
    public static final Config config = new Config();
    private String status = "loading script...";

    private Config(){} // hide constructor

    public static Config getConfig() {
        return config;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
