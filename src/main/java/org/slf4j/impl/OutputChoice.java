package org.slf4j.impl;

import java.io.PrintStream;

/**
 * This class encapsulates the user's choice of output target.
 *
 * @author Ceki G&uuml;lc&uuml;
 */
class OutputChoice {

    enum OutputChoiceType {
        SYS_OUT, CACHED_SYS_OUT, SYS_ERR, CACHED_SYS_ERR, FILE;
    }

    final OutputChoiceType outputChoiceType;
    final PrintStream targetPrintStream;

    OutputChoice(OutputChoiceType outputChoiceType) {
        this.outputChoiceType = outputChoiceType;
        if (outputChoiceType == OutputChoiceType.CACHED_SYS_OUT) {
            this.targetPrintStream = System.out;
        } else if (outputChoiceType == OutputChoiceType.CACHED_SYS_ERR) {
            this.targetPrintStream = System.err;
        } else {
            this.targetPrintStream = null;
        }
    }

    PrintStream getTargetPrintStream() {
        switch (outputChoiceType) {
            case SYS_OUT:
                return System.out;
            case SYS_ERR:
                return System.err;
            case CACHED_SYS_ERR:
            case CACHED_SYS_OUT:
            case FILE:
                return targetPrintStream;
            default:
                throw new IllegalArgumentException();
        }

    }

}
