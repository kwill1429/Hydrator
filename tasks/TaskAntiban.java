package tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.rs3.methods.widget.Camera;
import com.epicbot.api.util.Time;
import references.Global_Variable;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class TaskAntiban extends Node implements Task
{

    @Override
    public void run()
    {
        int antibanNumber = (int)(Global_Variable.randomNumber.nextGaussian()*10);
        if (antibanNumber > 13 && antibanNumber <= 17)//11.05
        {
            moveMouse();
        } else if (antibanNumber > 17 && antibanNumber <=22)//4.55
        {
            moveScreen();
        } else if (antibanNumber > 22 && antibanNumber <= 28)//1.7
        {
            focusNearByPlayer();
        } else if (antibanNumber > 28)//tiny
        {
            checkMagicXp();
        }
        Time.sleep(300, 2000);
    }

    @Override
    public boolean shouldExecute()
    {
        return Players.getLocal() != null;
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
