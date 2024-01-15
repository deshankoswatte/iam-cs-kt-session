package com.iam.cs.kt.session.oom;

/**
 * General object 1 used to demonstrate OOM.
 */
public class GeneralObjectOne {

    GeneralObjectTwo generalObjectTwo = new GeneralObjectTwo();

    /**
     * Nested call to general object two.
     */
    public void grow() {

        generalObjectTwo.grow();
    }
}
