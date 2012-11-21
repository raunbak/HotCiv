package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {TestAlphaCiv.class,
                TestBetaCiv.class,
                TestDecreasingAgeStrategy.class,
                TestGammaCiv.class,
                TestDeltaCiv.class
        })

public class TestAll {
}
