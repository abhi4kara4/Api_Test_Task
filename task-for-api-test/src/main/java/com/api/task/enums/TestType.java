package com.api.task.enums;

/**
 * Enumerator representing the test types.
 *
 * @author  Abhijit Karan
 */
public enum TestType {

    /** Functional test. */
    FUNCTIONAL("FUNCTIONAL"),

    /** Manual test, cannot be added as part of CI build. */
    MANUAL("MANUAL");
    
    private String testType;

    private TestType(final String testType) {
        this.testType = testType;
    }

    public String value() {
        return testType;
    }

    @Override
    public String toString() {
        return testType;
    }
}
