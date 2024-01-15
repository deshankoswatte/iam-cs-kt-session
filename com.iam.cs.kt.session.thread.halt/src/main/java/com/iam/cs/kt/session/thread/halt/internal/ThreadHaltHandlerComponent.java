package com.iam.cs.kt.session.thread.halt.internal;

import com.iam.cs.kt.session.thread.halt.ThreadHaltHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * OSGi bundle for the thread halt handler module.
 */
@Component(
        name = "com.iam.cs.kt.session.thread.halt.internal.ThreadHaltHandlerComponent",
        immediate = true
)
public class ThreadHaltHandlerComponent {

    private static final Log LOGGER = LogFactory.getLog(ThreadHaltHandlerComponent.class);

    /**
     * Activates/Registers the ThreadHaltHandlerComponent as an OSGi service.
     *
     * @param context The component context.
     */
    @Activate
    protected void activate(ComponentContext context) {

        try {
            BundleContext bundleContext = context.getBundleContext();
            bundleContext.registerService(AbstractEventHandler.class.getName(),
                    new ThreadHaltHandler(), null);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The thread halt handler component is enabled.");
            }
        } catch (Throwable throwable) {
            LOGGER.error("Error while activating the thread halt handler component.", throwable);
        }
    }

    /**
     * Deactivates the ThreadHaltHandlerComponent OSGi service.
     *
     * @param context The component context.
     */
    @Deactivate
    protected void deactivate(ComponentContext context) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The thread halt handler component is de-activated.");
        }
    }

    /**
     * Sets the realm service to the ThreadHaltHandlerDataHolder.
     *
     * @param realmService The realm service to be set to the ThreadHaltHandlerDataHolder.
     */
    @Reference(
            name = "realm.service",
            service = RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService")
    protected void setRealmService(RealmService realmService) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Setting the Realm Service.");
        }
        ThreadHaltHandlerDataHolder.getInstance().setRealmService(realmService);
    }

    /**
     * Unsets the realm service from the ThreadHaltHandlerDataHolder.
     *
     * @param realmService The realm service which unsets the current realm service from the
     *                     ThreadHaltHandlerDataHolder.
     */
    protected void unsetRealmService(RealmService realmService) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Unsetting the Realm Service.");
        }
        ThreadHaltHandlerDataHolder.getInstance().setRealmService(null);
    }
}
