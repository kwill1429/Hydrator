package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.Environment;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.filters.IdFilter;
import references.Constants;
import references.Global_Variable;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskStop extends Node implements Task
{

    @Override
    public void run()
    {
        Global_Variable.state = "Out of resources. Must stop.";
        Environment.getActiveScript().stop();
    }

    @Override
    public boolean shouldExecute()
    {
        boolean outOfTime = Global_Variable.timeRunning > (8*Constants.MS_TO_HR + Global_Variable.randomNumber.nextInt(30*Constants.MIN_TO_MS));
        return Global_Variable.done || outOfTime;
    }
}
