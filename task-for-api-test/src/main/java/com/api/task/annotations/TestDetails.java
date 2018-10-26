package com.api.task.annotations;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.api.task.enums.TestType;


/**
 * Annotation class to handle the Test Details to complement the test annotations supported by
 * TestNG.
 *
 * @author  Abhijit Karan
 */
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ METHOD })
public @interface TestDetails {

    /** The unique identifier for the test*/
    String testUID();

    /**Tags applied for the test*/
    String[] tags() default {};

    /** Type of test. Types can be
     * 1. FUNCTIONAL
     * 2. SMOKE
     * 3. PERFORMANCE
     * 4. MANUAL
     * Refer @see {@link ECatsTestTypes}
     * @return
     */
    TestType[] testType() default {};
    
    /** The category to which the test belongs to*/
    String testCategory() default "";

    /** Brief description of the test case*/
    String testDecription() default "";
}
