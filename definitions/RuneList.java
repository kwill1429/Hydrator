package definitions;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class RuneList
{
    //Complex Runes
    public static final Rune steamRune = new Rune("Steam rune", 4694);
    public static final Rune lavaRune = new Rune("Lava rune", 4699);
    public static final Rune mistRune = new Rune("Mist rune", 4695);
    public static final Rune mudRune = new Rune("Mud rune", 4698);
    public static final Rune smokeRune = new Rune("Smoke rune", 4697);

    //Simple Runes
    public static final Rune astralRune = new Rune("Astral rune", 9075);
    public static final Rune fireRune = new Rune("Fire rune", 554, steamRune, lavaRune, smokeRune);
    public static final Rune waterRune = new Rune("Water rune", 555, steamRune, mistRune, mudRune);

}
