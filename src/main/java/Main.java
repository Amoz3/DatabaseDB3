import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import task.impl.Test;


@ScriptManifest(name = "database test",
        description = "testing out my psql database logging methods!",
        author = "camalCase",
        version = 1.0, category = Category.MISC,
        image = "")

public class Main extends TaskScript {
    @Override
    public void onStart() {
        addNodes(new Test());

    }



}

