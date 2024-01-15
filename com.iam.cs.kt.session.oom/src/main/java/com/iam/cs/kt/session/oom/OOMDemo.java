package com.iam.cs.kt.session.oom;

/**
 * Main class which initiates the OOM issue.
 */
public class OOMDemo {

    /**
     * The method which starts the OOM issue.
     * @param args Arguments for the main method.
     */
    public static void main(String[] args) {

        GeneralObjectOne generalObjectOne = new GeneralObjectOne();
        generalObjectOne.grow();
    }
}
