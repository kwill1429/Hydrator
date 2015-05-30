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
    public void run()
    {
        Global_Variable.justBanked = false;
        if (!Tabs.BACKPACK.isOpen())
        {
            Global_Variable.state = "Opening\nBackpack";
            Tabs.BACKPACK.open();
        }
        Time.sleep(50,300);
        if (Players.getLocal().getAnimation() == -1)
        {
            Global_Variable.state = "Casting spell";
            Keyboard.sendKey('1');
            Global_Variable.spellCasts++;
        }
//        Time.sleep(350, 700);

    }

    @Override
    public boolean shouldExecute()
    {
        return Players.getLocal() != null && Inventory.contains(new IdFilter<Item>(true, 9075)) && Inventory.contains(new IdFilter<Item>(true, 434)) && !Global_Variable.state.contains("resting");
    }


}
