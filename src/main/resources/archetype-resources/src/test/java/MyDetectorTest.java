package ${package};

import static edu.umd.cs.findbugs.test.CountMatcher.containsExactly;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.test.matcher.BugInstanceMatcher;
import edu.umd.cs.findbugs.test.matcher.BugInstanceMatcherBuilder;
import edu.umd.cs.findbugs.test.SpotBugsExtension;
import edu.umd.cs.findbugs.test.SpotBugsRunner;

@ExtendWith(SpotBugsExtension.class)
public class MyDetectorTest {

    @Test
    public void testGoodCase(SpotBugsRunner spotbugs) {
        Path path = Path.of("target/test-classes", "${package}".replace('.', '/'), "GoodCase.class");
        BugCollection bugCollection = spotbugs.performAnalysis(path);

        BugInstanceMatcher bugTypeMatcher = new BugInstanceMatcherBuilder()
                .bugType("MY_BUG").build();
        assertThat(bugCollection, containsExactly(0, bugTypeMatcher));
    }

    @Test
    public void testBadCase(SpotBugsRunner spotbugs) {
        Path path = Path.of("target/test-classes", "${package}".replace('.', '/'), "BadCase.class");
        BugCollection bugCollection = spotbugs.performAnalysis(path);

        BugInstanceMatcher bugTypeMatcher = new BugInstanceMatcherBuilder()
                .bugType("MY_BUG").build();
        assertThat(bugCollection, containsExactly(1, bugTypeMatcher));
    }
}
