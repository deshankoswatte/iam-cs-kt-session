package com.iam.cs.kt.session.oom;

import java.util.HashMap;

/**
 * HashMap manager used to demonstrate OOM.
 * java -Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath="filePath" -cp
 * com.iam.cs.kt.session.oom/target/com.iam.cs.kt.session.oom-1.0.0-SNAPSHOT.jar com.iam.cs.kt.session.oom.OOMDemo
 */
public class HashMapManager {

    HashMap<Object, Object> hashMap = new HashMap<>();
    private static final String TEXT = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer" +
            " took a galley of type and scrambled it to make a type specimen book. It has survived not only five " +
            "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was " +
            "popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more " +
            "recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

    /**
     * Create unlimited hash map records to result in an OOM exception.
     */
    public void grow() {

        long counter = 0;
        while (true) {
            hashMap.put("key" + counter, TEXT);
            ++counter;
        }
    }
}
