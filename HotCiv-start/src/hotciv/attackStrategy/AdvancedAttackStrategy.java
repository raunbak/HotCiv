package hotciv.attackStrategy;

import hotciv.framework.*;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 22-11-12
 * Time: 12:27
 * To change this template use File | Settings | File Templates.
 */
public class AdvancedAttackStrategy implements AttackStrategy {
    Utility utility;
    @Override
    public Unit outcomeOfBattle(Game game, Position pAttacking, Position pDefending) {

        Unit unitAttacking = game.getUnitAt(pAttacking);
        Unit unitDefending = game.getUnitAt(pDefending);

        // Getting all values needed to calculate winner.
        int attStrength = unitAttacking.getAttackingStrength();
        int defStrength = unitDefending.getDefensiveStrength();

        int attSupport = utility.getFriendlySupport(game, pAttacking, unitAttacking.getOwner());
        int defSupport = utility.getFriendlySupport(game, pDefending, unitDefending.getOwner());

        int attTerrainFactor =   utility.getTerrainFactor(game, pAttacking);
        int defTerrainFactor =   utility.getTerrainFactor(game, pDefending);


        // Finding combined strength by: ( strength + support ) times terrainfactor
        int combinedAttStrength = ( attStrength + attSupport ) * attTerrainFactor;
        int combinedDefStrength = ( defStrength + defSupport ) * defTerrainFactor;

        // Die-rolls
        Die die = new Die(6);
        int attDieRoll = die.roll();
        int defDieRoll = die.roll();

        if (combinedAttStrength * attDieRoll > combinedDefStrength * defDieRoll) {
            return unitAttacking;
        }
        else {
            return unitDefending;
        }

    }
}
