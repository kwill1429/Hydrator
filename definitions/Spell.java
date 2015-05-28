package definitions;

import com.epicbot.api.os.methods.tab.Magic;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class Spell
{
    public final String name;
    public final Magic.Book spellbook;
    public final Rune[] runes;
    public final int[] numRunes;
    public final int spellSlot;

    Spell(String name, Magic.Book spellbook, int spellSlot, Rune[] runes, int[] numRunes)
    {
        this.name = name;
        this.spellbook = spellbook;
        this.spellSlot = spellSlot;
        this.runes = runes;
        this.numRunes = numRunes;
    }
}
