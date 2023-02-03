package org.azure.david.functions;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTriggerJava1 {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTriggerJava1")
    @StorageAccount("davidtorageccount1")
            //DefaultEndpointsProtocol=https;AccountName=davidtorageccount1;AccountKey=xaSYTlumGuqoK0SwjACPOTutoKUufN5taBX5N29JAwwTPTw6M3PJ/OjflDbNnX4WJQPRiJ5Iwx9p+AStaCpBUA==;EndpointSuffix=core.windows.net")
            //"davidtorageccount1")
    //DefaultEndpointsProtocol=https;AccountName=davidtorageccount1;AccountKey=xaSYTlumGuqoK0SwjACPOTutoKUufN5taBX5N29JAwwTPTw6M3PJ/OjflDbNnX4WJQPRiJ5Iwx9p+AStaCpBUA==;EndpointSuffix=core.windows.net
    //https://davidtorageccount1.blob.core.windows.net/con1  importcontainer-workitems
    public void run(
        @BlobTrigger(name = "content", path = "con1/{name}", dataType = "binary") byte[] content,
        @BindingName("name") String name,
        final ExecutionContext context
    ) {
        //httpclient
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");
    }
}