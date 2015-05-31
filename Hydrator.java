import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.filters.IdFilter;
import com.epicbot.event.events.MessageEvent;
import com.epicbot.event.listeners.MessageListener;
import com.epicbot.event.listeners.PaintListener;
import references.Global_Variable;
import tasks.TaskAntiban;
import tasks.TaskBank;
import tasks.TaskHydrate;
import tasks.TaskStop;
import utils.UtilPaint;

import java.awt.*;

@Manifest(name="Hydrator", author="Qwyll", game=GameType.RS3, version = 1.2)
public class Hydrator extends ActiveScript implements PaintListener, MessageListener
{

    //Tasks: Bank, Hydrate, Antiban
    private TaskBank taskBank;
    private TaskHydrate taskHydrate;
    private TaskAntiban taskAntiban;
    private TaskStop taskStop;
    private UtilPaint utilPaint = new UtilPaint();
    boolean toggleCast = false;
    boolean item = false;
    long timeStarted = 0;

    @Override
    public boolean onStart() {
        if (Players.getLocal() != null && Magic.canCastSpell(Magic.Spell.HUMIDIFY))
        {
            System.out.println("Starting");
            Global_Variable.init();
            Global_Variable.state = "Reading";


            taskBank = new TaskBank();
            provide(taskBank);
            taskHydrate = new TaskHydrate();
            provide(taskHydrate);
            taskAntiban = new TaskAntiban();
            provide(taskAntiban);
            taskStop = new TaskStop();
            provide(taskStop);
            return true;
        } else {
            System.out.println("Missing Level or Spellbook");
            this.stop();
            return false;
        }
    }

    @Override
    public void onStop()
    {
        revoke(taskBank);
        revoke(taskHydrate);
        revoke(taskAntiban);
        revoke(taskStop);

        terminated(taskBank);
        terminated(taskHydrate);
        terminated(taskAntiban);
        terminated(taskStop);

        this.kill();
    }

    @Override
    public void onRepaint(Graphics2D graphics2D) {
        if (!Global_Variable.state.contains("Initializing"))
        {
            utilPaint.init();
            utilPaint.createPaint(graphics2D);
        }
    }

    @Override
    public void messageReceived(MessageEvent messageEvent)
    {
        String msg = messageEvent.getMessage();
        String messageFinished = "item could not be found:";

        if( msg.toLowerCase().contains(messageFinished))
        {
            boolean invContainsClay = Inventory.contains(new IdFilter<Item>(true, 434));
            boolean invContainsAstral = Inventory.contains(new IdFilter<Item>(true, 9075));
            Global_Variable.done = !invContainsAstral || !invContainsClay;
        }
    }
}