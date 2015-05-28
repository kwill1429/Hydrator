package definitions;

/**
 * Created by Qwyll on 5/26/2015.
 */
public class Rune extends Item
{
    public final Rune[] equivalentRunes;
    public final int[] equivalentRunesIds;
    public final Staff[] equivalentSaves;
    public final int[] equivalentSavesId;

    Rune(String name, int id, Rune[] equivalentRunes, Staff[] equivalentSaves)
    {
        super(name, id);
        this.equivalentRunes = equivalentRunes;
        this.equivalentRunesIds = getEquivilentIds(equivalentRunes);
        this.equivalentSaves = equivalentSaves;
        this.equivalentSavesId = getEquivilentIds(equivalentSaves);
    }

    private <T extends Item> int[] getEquivilentIds(T[] equivilentItem)
    {
        int[] ids = new int[equivilentItem.length+1];
        ids[0] = this.id;
        for (int i = 1; i < ids.length; i++)
        {
            ids[i] = equivilentItem[i].id;
        }
        return ids;
    }
}