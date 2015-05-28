package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Keyboard;
import com.epicbot.api.rs3.methods.interactive.NPCs;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.epicbot.api.rs3.methods.widget.Camera;
import com.epicbot.api.rs3.wrappers.Locatable;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Filter;
import com.epicbot.api.util.Time;
import com.epicbot.api.util.filters.IdFilter;
import com.epicbot.client.rs3.api.NPC;
import com.epicbot.client.rs3.api.Viewport;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskBank extends Node implements Task
{

    @Override
    public void run()
    {
        if (Bank.BankLocations.atBank())
        {
            if (!NPCs.getNearest(new IdFilter<com.epicbot.api.rs3.wrappers.interactive.NPC>(true, Bank.BANKERS)).isOnScreen())
                Camera.turnTo(NPCs.getNearest(new IdFilter<com.epicbot.api.rs3.wrappers.interactive.NPC>(true, Bank.BANKERS)));
            Bank.open();

            if (Bank.isOpen())
            {
                Time.sleep(200, 500);
                Keyboard.sendKey('1');
            }
        }
    }

    @Override
    public boolean shouldExecute()
    {
        return !(Inventory.contains(new IdFilter<Item>(true, 9075)) && Inventory.contains(new IdFilter<Item>(true, 434))) ? true : false;
    }
}
