package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Keyboard;
import com.epicbot.api.rs3.methods.Tabs;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Time;
import com.epicbot.api.util.filters.IdFilter;
import references.Global_Variable;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskHydrate extends Node implements Task
{

    @Override
    public void run() //Time 3 Ticks
    {
        if (!Tabs.BACKPACK.isOpen())
        {
            Global_Variable.setState("Opening\nBackpack");
            Tabs.BACKPACK.open();
        }
        Time.sleep(10,100);
        if (Players.getLocal().getAnimation() == -1)
        {
            Global_Variable.setState("Casting spell");
            Keyboard.sendKey('1');
            Time.sleep(1900, 1900+200);
            Global_Variable.spellCasts++;
        }

    }

    @Override
    public boolean shouldExecute()
    {
        boolean invContainsAstral = Inventory.contains(new IdFilter<Item>(true, 9075));
        boolean invContainsClay = Inventory.contains(new IdFilter<Item>(true, 434));
        boolean playerExists = Players.getLocal() != null;
        boolean scriptResting = Global_Variable.state.contains("resting");

        return playerExists &&
                invContainsAstral &&
                invContainsClay &&
                !scriptResting;
    }


}
