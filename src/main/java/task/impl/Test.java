package task.impl;

import classes.Database;
import task.AbstractTask;

public class Test extends AbstractTask {
    @Override
    public boolean accept() {
        return true;
    }

    @Override
    public int execute() {
        log("testing...");
        if(Database.updateCol("gp", "1000")) {
            log("TRUE!");
        }
        config.setStatus("tested");

        return 3000;
    }
}
