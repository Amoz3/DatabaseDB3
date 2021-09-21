package task;

import config.Config;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.TaskNode;

public abstract class AbstractTask extends TaskNode {
    // Gets an instance of Config object to use in TaskNode
    protected Config config = Config.getConfig();
    protected ScriptManager manager = ScriptManager.getScriptManager();

    @Override
    public abstract boolean accept();

    @Override
    public abstract int execute();
}
