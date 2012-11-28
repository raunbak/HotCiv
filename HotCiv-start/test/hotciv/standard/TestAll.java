package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {TestAlphaCiv.class,
                TestBetaCiv.class,
                TestDecreasingAgeStrategy.class,
                TestGammaCiv.class,
                TestDeltaCiv.class,
                TestUtility.class,
                TestWinByAttacksStrategy.class,
                TestAdvancedAttackStrategy.class,
                TestEpsilonCiv.class,
                TestInvalidUnitCreation.class,
                TestAdvancedAttackStrategy.class,
                TestZetaCiv.class
        })

public class TestAll {
}
