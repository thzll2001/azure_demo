package org.azure.david.functions;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTriggerJava1 {

    private static HttpRequest createHttpclient(String uri, int port,
                                                final ExecutionContext context) {

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .headers("Content-Type", "text/plain;charset=UTF-8")
                    .GET()
                    .build();
        }catch (Exception e){
            context.getLogger().info(e.getMessage());
        }
        return request ;
    }

    private static HttpResponse<InputStream> getResponse(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofInputStream());
    }

    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTriggerJava1")
    //@StorageAccount("davidtorageccount1")
    public void run(
        @BlobTrigger(name = "content", path = "con1/{name}", dataType = "binary", connection = "AzureStorageConnection")  byte[] content,
        @BindingName("name") String name,
        final ExecutionContext context
    ) {
        //httpclient
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");
    }
}