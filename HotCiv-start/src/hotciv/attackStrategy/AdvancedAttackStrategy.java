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
    public Unit outcomeOfBattle(Game game, Position unitAttacting, Position unitDefending) {

        int CombinedAttStrength;
        int CombinedDefStrength;

        // Getting all values needed to calculate winner.
        int AttStrength = game.getUnitAt(unitAttacting).getAttackingStrength();
        int DefStrength = game.getUnitAt(unitDefending).getDefensiveStrength();
        int AttSupport = utility.getFriendlySupport(game, unitAttacting, game.getUnitAt(unitAttacting).getOwner());
        int DefSupport = utility.getFriendlySupport(game, unitDefending, game.getUnitAt(unitDefending).getOwner());

        int AttTerrainFactor =   utility.getTerrainFactor(game, unitAttacting);
        int DefTerrainFactor =   utility.getTerrainFactor(game, unitDefending);


        // Finding combined strength by : ( Att + support ) times terrainfactor
        CombinedAttStrength = ( AttStrength + AttSupport ) * AttTerrainFactor;
        CombinedDefStrength = ( DefStrength + DefSupport ) * DefTerrainFactor;

        // This might be overkill. But had a die class already made.
        Die AttDie = new Die(6);
        Die DefDie = new Die(6);
        AttDie.roll();
        DefDie.roll();

        int AttDieRoll = AttDie.getEyes();
        int DefDieRoll = DefDie.getEyes();

        if (CombinedAttStrength * AttDieRoll > CombinedDefStrength * DefDieRoll) {
            return game.getUnitAt(unitAttacting);
        }
        else {
            return  game.getUnitAt(unitDefending);
        }

    }
}
