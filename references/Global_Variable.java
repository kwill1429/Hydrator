package references;

import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.filters.IdFilter;
import com.epicbot.api.util.prices.rs3.GrandExchange;

import java.util.Random;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class Global_Variable
{

    public static long startTime;
    public static int startExp;
    public static int spellCasts;
    public static int itemsMade;
    public static Random randomNumber;
    public static boolean justBanked;
    public static boolean done;
    public static int moneyEarned;
    public static boolean spellCasted;
    public static String state;

    public static long timeRunning;

    public static int marketPriceAstral;
    public static int marketPriceClay;
    public static int marketPriceSoftClay;

    public static void init()
    {
        Global_Variable.state = "Initializing";
        Global_Variable.startTime = System.currentTimeMillis();
        Global_Variable.startExp = Skills.Skill.MAGIC.getExperience();
        Global_Variable.spellCasts = 0;
        Global_Variable.itemsMade = 0;
        Global_Variable.moneyEarned = 0;
        Global_Variable.randomNumber = new Random();
        Global_Variable.done=false;
        Global_Variable.spellCasted = !Inventory.contains(new IdFilter<Item>(true, 434));
        Global_Variable.marketPriceAstral = GrandExchange.loadItemInfo(9075).getMarketPrice();
        Global_Variable.marketPriceClay = GrandExchange.loadItemInfo(434).getMarketPrice();
        Global_Variable.marketPriceSoftClay = GrandExchange.loadItemInfo(1761).getMarketPrice();
    }

    public static void setState(String state)
    {
        Global_Variable.state = state;
        System.out.println(state);
    }
}
