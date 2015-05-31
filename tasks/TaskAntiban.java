package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.rs3.methods.widget.Camera;
import com.epicbot.api.util.Time;
import references.Global_Variable;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskAntiban extends Node implements Task
{

    @Override
    public void run()
    {
        double antibanNumber = (Global_Variable.randomNumber.nextGaussian()*6);
        if (antibanNumber > 5 && antibanNumber <= 9)//11.05
        {
            Global_Variable.state = "Moving mouse.";
            moveMouse();
        } else if (antibanNumber > 9 && antibanNumber <=12)//4.55
        {
            Global_Variable.state = "Moving screen.";
            moveScreen();
        } else if (antibanNumber > 12 && antibanNumber <= 16)//1.7
        {
            Global_Variable.state = "Focusing player.";
            focusNearByPlayer();
        } else if (antibanNumber > 16)//tiny
        {
            Global_Variable.state = "Checking xp.";
            checkMagicXp();
        }
        Time.sleep(100, 600);
    }

    @Override
    public boolean shouldExecute()
    {
        boolean playerExists = Players.getLocal() != null;
        boolean scriptResting = Global_Variable.state.contains("resting");
        return playerExists && !scriptResting;
    }

    private void moveMouse()
    {
        int num = Global_Variable.randomNumber.nextInt(100);
        if( num < 46 )
        {
            Mouse.moveRandomly(100, 400);
        }
        if( num > 45 && num < 90 )
        {
            Mouse.moveOffScreen();
        }
    }

    private void moveScreen()
    {
        int yaw = Camera.getYaw() + Global_Variable.randomNumber.nextInt(360)-180;
        int pitch = Camera.getPitch() + Global_Variable.randomNumber.nextInt(360)-180;
        if( pitch < 0 )
        {
            pitch = 0;
        }
        else if( pitch > 90)
        {
            pitch = 90;
        }
        Camera.setAngle(yaw);
        Camera.setPitch(pitch);
    }

    private void focusNearByPlayer()
    {

    }

    private void checkMagicXp()
    {
        Skills.Skill.MAGIC.hover(2375);
    }
}
