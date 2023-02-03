package org.azure.david.functions;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerBlob {
    /**
     * This function listens at endpoint "/api/HttpTriggerBlob". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTriggerBlob
     * 2. curl {your host}/api/HttpTriggerBlob?name=HTTP%20Query

     @FunctionName("download")
     public HttpResponseMessage downloadFile(
     @HttpTrigger(
     name = "request",
     methods = {GET},
     authLevel = ANONYMOUS)
     final HttpRequestMessage<Optional<String>> request,
     final ExecutionContext context)


     @BlobInput(
     name = "file",
     dataType = "binary",
     path = "java-functions-container/test.png",
     connection = "AzureWebJobsStorage")
     byte[] content,
     final ExecutionContext context)
     */
    @FunctionName("HttpTriggerBlob")
//    @StorageAccount("Storage_Account_Connection_String")
    public HttpResponseMessage blobSize(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @BlobInput(
                    name = "file",
                    dataType = "binary",
                    path = "samples-workitems/{Query.file}")
            byte[] content,
            final ExecutionContext context) {
        // build HTTP response with size of requested blob
        return request.createResponseBuilder(HttpStatus.OK)
                .body("The size of \"" + request.getQueryParameters().get("file") + "\" is: " + content.length + " bytes")
                .build();
    }
}
