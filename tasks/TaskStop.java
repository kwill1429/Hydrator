package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.Environment;
import com.epicbot.api.rs3.methods.Game;
import com.epicbot.api.rs3.methods.Widgets;
import com.epicbot.api.util.Time;
import references.Global_Variable;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskStop extends Node implements Task
{

    @Override
    public void run()
    {
        System.out.println("Out of resources. Must stop.");
//        Widgets.getChild(93782082).interact("Open Settings");
        Widgets.getChild(93782082).click(true);
        Widgets.getChild(93913163).interact("Select");
        Environment.getActiveScript().stop();
    }

    @Override
    public boolean shouldExecute()
    {
        return (Global_Variable.cantHydrate && Global_Variable.cantBank) ? true : true;
    }
}
