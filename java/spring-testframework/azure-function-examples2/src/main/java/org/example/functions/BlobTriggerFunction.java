package org.example.functions;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTriggerFunction {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTrigger-Java")
    public void run(
            @BlobTrigger(name = "file", dataType = "binary", path = "con1/{name}", connection = "AzureStorageDemoConnectionStringSrc") byte[] content,
            @BindingName("name") String filename, final ExecutionContext context)
            throws InvalidKeyException, URISyntaxException, StorageException {

        // Insert custom code
//        CloudStorageAccount storageAccountDest;
//        CloudBlobClient blobClientDest = null;
//        CloudBlobContainer containerDest = null;

//        String storageConnectionStringDest = System.getenv("AzureStorageDemoConnectionStringDest");

        // Parse the connection string and create a blob client to interact with Blob
        // storage
//        storageAccountDest = CloudStorageAccount.parse(storageConnectionStringDest);
//        blobClientDest = storageAccountDest.createCloudBlobClient();
//        containerDest = blobClientDest.getContainerReference("files2");
//
//        // Create the container if it does not exist with public access.
//        context.getLogger().info("Creating container: " + containerDest.getName());
//        containerDest.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(),
//                new OperationContext());
//
//        CloudBlob blobDest = containerDest.getBlockBlobReference(filename);
//        try {

            context.getLogger().info("Start Uploading blob: " + filename);
            context.getLogger().info("Java Blob trigger function processed a blob  Size: " + content.length + " Bytes");

//            blobDest.uploadFromByteArray(content, 0, content.length);
            context.getLogger().info("Finished Uploading blob: " + filename);

//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

//    @StorageAccount("MyStorageConnectionAppSetting")
//    public void run(
//        @BlobTrigger(name = "content", path = "mycontainer/{name}", dataType = "binary") byte[] content,
//        @BindingName("name") String name,
//        final ExecutionContext context
//    ) {
//        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");
//    }
}
