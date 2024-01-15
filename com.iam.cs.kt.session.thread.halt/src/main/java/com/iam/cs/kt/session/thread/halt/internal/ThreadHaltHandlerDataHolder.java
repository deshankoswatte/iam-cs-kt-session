package com.iam.cs.kt.session.thread.halt.internal;

import org.wso2.carbon.user.core.service.RealmService;

/**
 * This class contains the services that are needed for the thread halt module.
 */
public class ThreadHaltHandlerDataHolder {

    private static final ThreadHaltHandlerDataHolder INSTANCE = new ThreadHaltHandlerDataHolder();
    private RealmService realmService = null;

    /**
     * Private constructor to make sure that only a single object will exist.
     */
    private ThreadHaltHandlerDataHolder() {

    }

    /**
     * Retrieves an instance of the ThreadHaltHandlerDataHolder.
     *
     * @return An instance of the ThreadHaltHandlerDataHolder.
     */
    public static ThreadHaltHandlerDataHolder getInstance() {

        return INSTANCE;
    }

    /**
     * Retrieves the realm service.
     *
     * @return The realm service.
     */
    public RealmService getRealmService() {

        return realmService;
    }

    /**
     * Sets the realm service.
     *
     * @param realmService The realm service to be set.
     */
    public void setRealmService(RealmService realmService) {

        this.realmService = realmService;
    }
}
