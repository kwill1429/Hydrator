package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Keyboard;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.Tabs;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Time;
import com.epicbot.api.util.Timer;
import com.epicbot.api.util.filters.IdFilter;
import javafx.scene.control.Tab;
import references.Global_Variable;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskHydrate extends Node implements Task
{

    @Override
    public void run()
    {
        Global_Variable.cantHydrate = false;
        if (!Tabs.BACKPACK.isOpen())
            Tabs.BACKPACK.open();
        Time.sleep(400,900);
        if (Players.getLocal().getAnimation() == -1)
        {
            System.out.println("Casting spell");
            Keyboard.sendKey('1');
            Global_Variable.spellCasts++;
        }
        Time.sleep(350, 700);

    }

    @Override
    public boolean shouldExecute()
    {
        if (Players.getLocal() != null)
            if (canCast())
                return true;
        if (Global_Variable.cantHydrate == true)
        {
            Global_Variable.cantBank = true;
        }
        Global_Variable.cantHydrate = true;
        return false;
    }

    private boolean canCast()
    {
        if (Inventory.contains(new IdFilter<Item>(true, 9075)) && Inventory.contains(new IdFilter<Item>(true, 434)))
            return true;
        return false;
    }

//    private boolean hasRunes(Rune[] runes)
//    {
//        boolean hasNeededRunes = true;
//        //for each needed rune is there a valid item to compensate
//        for (Rune rune : runes)
//        {
//            if (!Inventory.contains(new IdFilter<Item>(rune.equivalentRunesIds)) || )
//            {
//                hasNeededRunes = false;
//            }
//        }
//
//        return hasNeededRunes;
//    }


}
