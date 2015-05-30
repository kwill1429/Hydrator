package references;

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
}
