package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Keyboard;
import com.epicbot.api.rs3.methods.interactive.NPCs;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.epicbot.api.rs3.methods.widget.Camera;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Time;
import com.epicbot.api.util.filters.IdFilter;
import com.epicbot.api.util.prices.rs3.GrandExchange;
import references.Global_Variable;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskBank extends Node implements Task
{
    boolean bankClicked=false;

    @Override
    public void run()
    {
        if (Bank.BankLocations.atBank())
        {
            if (!NPCs.getNearest(new IdFilter<com.epicbot.api.rs3.wrappers.interactive.NPC>(true, Bank.BANKERS)).isOnScreen())
            {
                Global_Variable.state = "Looking at banker";
                Camera.turnTo(NPCs.getNearest(new IdFilter<com.epicbot.api.rs3.wrappers.interactive.NPC>(true, Bank.BANKERS)));
            }
            Global_Variable.state = "Opening bank";

            if (!Bank.isOpen())
            {
                if (Players.getLocal().getAnimation() != -1)
                    Time.sleep(600*3,600*5);
                System.out.println(Bank.open());
            }

            if (Bank.isOpen())
            {
                Time.sleep(50, 300);
                Global_Variable.state = "Getting financial data";
                int prevMake = Inventory.getCount(true, new IdFilter<Item>(true, 1761));
                Global_Variable.moneyEarned += getProfit(prevMake);
                Global_Variable.itemsMade += prevMake;
                Global_Variable.state = "Exchanging items";
                Keyboard.sendKey('1');
                Time.sleep(100);
            }
        }
    }

    @Override
    public boolean shouldExecute()
    {
        return !(Inventory.contains(new IdFilter<Item>(true, 9075)) && Inventory.contains(new IdFilter<Item>(true, 434))) && !Global_Variable.done && !Global_Variable.state.contains("resting");
    }

    private int getProfit(int itemsMade)
    {
        int cost,gross;
        cost = Global_Variable.marketPriceClay+(Global_Variable.marketPriceAstral/(itemsMade > 0 ? itemsMade : 1));
        gross = Global_Variable.marketPriceSoftClay;
        return (gross-cost)*itemsMade;
    }
}
