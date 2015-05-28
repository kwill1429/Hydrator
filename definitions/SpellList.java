package definitions;

import com.epicbot.api.os.methods.tab.Magic;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class SpellList
{
    public static final Spell humidify = new Spell("Humidify", // Name
                                                    Magic.Book.LUNAR, //Spellbook
                                                    4, //Spell slot
                                                    new Rune[] {RuneList.astralRune, RuneList.fireRune, RuneList.waterRune}, //Runes
                                                    new int[] {1, 1, 3} //Rune count
                                                    );
}
