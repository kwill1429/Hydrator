package utils;

import com.epicbot.api.rs3.methods.Environment;
import com.epicbot.api.rs3.methods.Game;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.Time;
import com.epicbot.api.util.prices.rs3.GrandExchange;
import references.Global_Variable;

import java.awt.*;

public class UtilPaint
{
    private int xpGained, xpPerHour;
    private double restTime;

    private final int frameTopX = 0;
    private int frameTopY, textTopX, textTopY;
    private final int frameWidth = 500;
    private final int frameHeight = 150;

    private String scriptInfoString, runtimeString, xpString;
    private String xpPerHrString, numOfTimesCast, castsPerHr;
    private String goldString, goldPerHourString, itemsMadeString;

    private final Color frameColor = new Color(0, 0, 0, 175);
    private final Color textColor = new Color(26, 73, 255, 255);
    private final Color versionColor = new Color(255, 255, 255, 255);

    private final Font versionFont = new Font("Helvetica Neue", Font.BOLD, 13);
    private final Font labelFont = new Font("Helvetica Neue", Font.PLAIN, 13);
    private final Font titleFont = new Font ("Helvetica Neue", Font.BOLD, 24);

    public void init()
    {
        restTime = Global_Variable.randomNumber.nextInt(60000*30)+60000*60*1.5;
    }

    public void createPaint(Graphics g)
    {
        frameTopY = Game.getHeight() - 350;
        textTopX = frameTopX + 150;
        textTopY = frameTopY + 25;

        // Draws the background
        g.setColor(frameColor);
        g.fillRoundRect(frameTopX, frameTopY, frameWidth, frameHeight, 10, 10);

        this.gatherStats();

        // Draws the script title
        g.setColor(textColor);
        g.setFont(titleFont);
        g.drawString(scriptInfoString, textTopX + 115, textTopY);

        // Draws the first column of stats
        g.setFont(labelFont);
        g.drawString(runtimeString, textTopX, textTopY + 25);
        g.drawString(xpString, textTopX, textTopY + 50);
        g.drawString(xpPerHrString, textTopX, textTopY + 75);
        g.drawString(itemsMadeString, textTopX, textTopY + 100);
        g.drawString("State: ", textTopX - 145, textTopY + 25);
        g.drawString("    "+Global_Variable.state, textTopX - 145, textTopY + 50);

        // Draws the second column of stats
        g.drawString(numOfTimesCast, textTopX + 215, textTopY + 25);
        g.drawString(castsPerHr, textTopX + 215, textTopY + 50);
        g.drawString(goldString, textTopX + 215, textTopY + 75);
        g.drawString(goldPerHourString, textTopX + 215, textTopY + 100);

        // Draws the version number
        g.setColor(versionColor);
        g.setFont(versionFont);
    }

    public void gatherStats()
    {
        xpGained = Skills.Skill.MAGIC.getExperience()-Global_Variable.startExp;
        Global_Variable.timeRunning = System.currentTimeMillis() - Global_Variable.startTime;
        xpPerHour = SkillData.MAGIC.getXpPerHour(Global_Variable.timeRunning);

        scriptInfoString = Environment.getActiveScript().getName();

        runtimeString = "Runtime: " + Time.format(Global_Variable.timeRunning);
        numOfTimesCast = "Num of Casts: " + Global_Variable.spellCasts;
        itemsMadeString = "Items made: " + Global_Variable.itemsMade;
        castsPerHr = "Casts/hr: " + Time.getPerHour(Global_Variable.spellCasts, Global_Variable.startTime);

        xpString = "XP Gained: " + xpGained;
        xpPerHrString = "XP/Hr: " + xpPerHour;

        if (Global_Variable.timeRunning % 6000000 == 0)
        {
            Global_Variable.marketPriceAstral = GrandExchange.loadItemInfo(9075).getMarketPrice();
            Global_Variable.marketPriceClay = GrandExchange.loadItemInfo(434).getMarketPrice();
            Global_Variable.marketPriceSoftClay = GrandExchange.loadItemInfo(1761).getMarketPrice();
        } else if (Global_Variable.timeRunning % restTime == 0)
        {
            Global_Variable.state = "resting";
            Time.sleep(60000*Global_Variable.randomNumber.nextInt(9)+1);
            restTime = Global_Variable.randomNumber.nextInt(60000*30)+60000*60*1.5;
        }

        goldString = "Gold Gained: "+ Global_Variable.moneyEarned;
        goldPerHourString = "Gold per Hour: " + (int)( ((double)Global_Variable.moneyEarned*1000*60*60)/(Global_Variable.timeRunning) );

    }


}