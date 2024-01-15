package com.iam.cs.kt.session.oom;

/**
 * General object 2 used to demonstrate OOM.
 */
public class GeneralObjectTwo {

    HashMapManager hashMapManager = new HashMapManager();

    /**
     * Nested call to hash map manager.
     */
    public void grow() {

        hashMapManager.grow();
    }
}
