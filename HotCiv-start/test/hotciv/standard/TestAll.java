package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                TestAdvancedAttackStrategy.class,
                TestAlphaCiv.class,
                TestBetaCiv.class,
                TestDecreasingAgeStrategy.class,
                TestDeltaCiv.class,
                TestEpsilonCiv.class,
                TestEtaCiv.class,
                TestGammaCiv.class,
                TestInvalidUnitCreation.class,
                TestUtility.class,
                TestWinByAttacksStrategy.class,
                TestZetaCiv.class
        })

public class TestAll {
}
