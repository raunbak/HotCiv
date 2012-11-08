package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

import java.security.PrivateKey;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 08-11-12
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class UnitImpl implements Unit{
    private String unittype;
    private Player owner;

    public UnitImpl(String unittype, Player owner){
        this.unittype = unittype;
        this.owner = owner;
        }

    @Override
    public String getTypeString() {
        return unittype;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Player getOwner() {
        return owner;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMoveCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getDefensiveStrength() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getAttackingStrength() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
