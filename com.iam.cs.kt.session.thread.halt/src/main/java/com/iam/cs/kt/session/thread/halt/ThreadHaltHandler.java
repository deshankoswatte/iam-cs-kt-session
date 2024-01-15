package com.iam.cs.kt.session.thread.halt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.bean.context.MessageContext;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.event.Event;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Handler which calls an external API on post authentication events to demonstrate a thread halt.
 */
public class ThreadHaltHandler extends AbstractEventHandler {

    private static final Log LOGGER = LogFactory.getLog(ThreadHaltHandler.class);
    private static final String API_URL = "https://www.google.com";

    /**
     * Handles the post authentication event which is captured by this handler.
     *
     * @param event The post authentication event which has been captured by the handler.
     * @throws IdentityEventException If there is an error while handling the external API call.
     */
    @Override
    public void handleEvent(Event event) throws IdentityEventException {

        try {
            makeApiCall();
        } catch (IOException ioException) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("An error occurred while calling the API URL.", ioException);
            }
            throw new IdentityEventException("An error occurred while calling the API URL.", ioException);
        }
    }

    /**
     * Calls an external API with a random sleep time.
     *
     * @throws IOException If there is an issue while processing the API call.
     */
    private static void makeApiCall() throws IOException {

        // Generate a random delay between 2 and 8 seconds
        int randomDelay = new Random().nextInt(6000) + 2000; // 2000 to 8000 milliseconds

        try {
            Thread.sleep(randomDelay);
        } catch (InterruptedException interruptedException) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The thread was interrupted.", interruptedException);
            }
        }

        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("GET");

        // Get the response
        int responseCode = connection.getResponseCode();

        // Read the response data
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Print the response
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("API Response: " + response);
            }

            // Close the BufferedReader
            in.close();
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("API call failed. Response Code: " + responseCode);
            }
        }

        // Disconnect the connection
        connection.disconnect();
    }

    /**
     * Retrieves the name of the handler.
     *
     * @return Name of the handler.
     */
    @Override
    public String getName() {

        return "testEventHandler";
    }

    /**
     * Returns the priority level of the handler.
     *
     * @param messageContext The message context.
     * @return Priority level of the handler.
     */
    @Override
    public int getPriority(MessageContext messageContext) {

        return 50;
    }
}
