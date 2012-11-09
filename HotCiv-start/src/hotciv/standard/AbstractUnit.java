package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 09-11-12
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractUnit implements Unit
{

    protected Player owner;
    protected String unittype;
    protected int defensiveStrength;
    protected int attackingStrength;

    @Override
    public String getTypeString() {
        return unittype;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return attackingStrength;
    }


}
