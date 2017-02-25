package ${package};

import org.apache.bcel.Const;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.bcel.OpcodeStackDetector;

public class MyDetector extends OpcodeStackDetector {
    private final BugReporter bugReporter;

    public MyDetector(BugReporter bugReporter) {
        this.bugReporter = bugReporter;
    }

    @Override
    public void sawOpcode(int seen) {
        if (seen != Const.GETSTATIC) {
            return;
        }
        if (getClassConstantOperand().equals("java/lang/System")
                && getNameConstantOperand().equals("out")) {
            // report bug when System.out is used in code
            BugInstance bug = new BugInstance(this, "MY_BUG", NORMAL_PRIORITY)
                    .addClassAndMethod(this)
                    .addSourceLine(this, getPC());
            bugReporter.reportBug(bug);
        }
    }
}
