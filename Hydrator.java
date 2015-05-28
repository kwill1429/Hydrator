import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.event.listeners.PaintListener;
import references.Global_Variable;
import tasks.TaskAntiban;
import tasks.TaskBank;
import tasks.TaskHydrate;
import tasks.TaskStop;

import java.awt.*;
import java.util.Date;
import java.util.Random;

@Manifest(name="Hydrator", author="Qwyll", game=GameType.RS3)
public class Hydrator extends ActiveScript implements PaintListener
{

    //Tasks: Bank, Hydrate, Antiban
    private TaskBank taskBank;
    private TaskHydrate taskHydrate;
    private TaskAntiban taskAntiban;
    private TaskStop taskStop;

    @Override
    public boolean onStart() {
        if (Players.getLocal() != null && Magic.canCastSpell(Magic.Spell.HUMIDIFY))
        {
            System.out.println("Starting");
            Global_Variable.startTime = new Date();
            Global_Variable.startExp = Skills.Skill.MAGIC.getExperience();
            Global_Variable.spellCasts = 0;
            Global_Variable.randomNumber = new Random();

//            taskBank = new TaskBank();
//            provide(taskBank);
//            taskHydrate = new TaskHydrate();
//            provide(taskHydrate);
//            taskAntiban = new TaskAntiban();
//            provide(taskAntiban);
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
        System.out.println("Times Cast: "+Global_Variable.spellCasts+" \nXP Gained: "+ (Skills.Skill.MAGIC.getExperience()-Global_Variable.startExp) + " \nXP per Minute: "+(((double)(Skills.Skill.MAGIC.getExperience() - Global_Variable.startExp)*(1000*60*60.0))/(((new Date()).getTime()-Global_Variable.startTime.getTime()))));
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

    }
}