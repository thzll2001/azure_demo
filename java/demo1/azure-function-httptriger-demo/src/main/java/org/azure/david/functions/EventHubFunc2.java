package org.azure.david.functions;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.*;

/**
 * Azure Functions with Event Hub trigger.
 */
public class EventHubFunc2 {
    /**
     * This function will be invoked when an event is received from Event Hub.
     */
//    @FunctionName("eventHubFunc2")
//    public void run(
//        @EventHubTrigger(name = "message", eventHubName = "eventhubent", connection = "CONNECTION_davidhub1", consumerGroup = "$Default", cardinality = Cardinality.MANY) List<String> message,
//        final ExecutionContext context
//    ) {
//        context.getLogger().info("Java Event Hub trigger function executed.");
//        context.getLogger().info("Length:" + message.size());
//        message.forEach(singleMessage -> context.getLogger().info(singleMessage));
//    }
    @FunctionName("eventHubFunc2")
    public void logEvent(
            @EventGridTrigger(
                    name = "event"
            )
            String content,
            final ExecutionContext context) {
        context.getLogger().info("Event content: " + content);
    }
//    public void logEvent(
//            @EventGridTrigger(
//                    name = "event"
//            )
//            EventSchema event,
//            final ExecutionContext context) {
//        context.getLogger().info("Event content: ");
//        context.getLogger().info("Subject: " + event.subject);
//        context.getLogger().info("Time: " + event.eventTime); // automatically converted to Date by the runtime
//        context.getLogger().info("Id: " + event.id);
//        context.getLogger().info("Data: " + event.data);
//    }
}
