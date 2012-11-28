package hotciv.attackStrategy;

import hotciv.framework.*;

/**
 *
 */
public class AdvancedAttackStrategy implements AttackStrategy {
    @Override
    public ModifiableUnit outcomeOfBattle(World world, Position pAttacking, Position pDefending) {

        ModifiableUnit unitAttacking = world.getUnitAt(pAttacking);
        ModifiableUnit unitDefending = world.getUnitAt(pDefending);

        // Getting all values needed to calculate winner.
        int attStrength = unitAttacking.getAttackingStrength();
        int defStrength = unitDefending.getDefensiveStrength();

        int attSupport = Utility.getFriendlySupport(world, pAttacking, unitAttacking.getOwner());
        int defSupport = Utility.getFriendlySupport(world, pDefending, unitDefending.getOwner());

        int attTerrainFactor =   Utility.getTerrainFactor(world, pAttacking);
        int defTerrainFactor =   Utility.getTerrainFactor(world, pDefending);


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
