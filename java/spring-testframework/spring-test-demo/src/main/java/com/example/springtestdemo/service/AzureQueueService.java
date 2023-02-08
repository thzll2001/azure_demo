package com.example.springtestdemo.service;

import com.azure.core.util.*;
import com.azure.storage.queue.*;
import com.azure.storage.queue.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class AzureQueueService {
    @Autowired
    private Environment env;

    final String CONNNECT_AZURE_QUEUE= "CONNNECT_AZURE_QUEUE" ;
    final String getConnectStr(){
        return env.getProperty(CONNNECT_AZURE_QUEUE);
    }
    final String connectStr ="";
    public static String createQueue(String connectStr)
    {
        try
        {
            // Create a unique name for the queue
            String queueName = "queue-" + java.util.UUID.randomUUID();

            System.out.println("Creating queue: " + queueName);

            // Instantiate a QueueClient which will be

            // used to create and manipulate the queue
            QueueClient queue = new QueueClientBuilder()
                    .connectionString(connectStr)
                    .queueName(queueName)
                    .buildClient();

            // Create the queue
            queue.create();
            return queue.getQueueName();
        }
        catch (QueueStorageException e)
        {
            // Output the exception message and stack trace
            System.out.println("Error code: " + e.getErrorCode() + "Message: " + e.getMessage());
            return null;
        }
    }
    public static void addQueueMessage
            (String connectStr, String queueName, String messageText)
    {
        try
        {
            // Instantiate a QueueClient which will be
            // used to create and manipulate the queue
            QueueClient queueClient = new QueueClientBuilder()
                    .connectionString(connectStr)
                    .queueName(queueName)
                    .buildClient();

            System.out.println("Adding message to the queue: " + messageText);

            // Add a message to the queue
            queueClient.sendMessage(messageText);
        }
        catch (QueueStorageException e)
        {
            // Output the exception message and stack trace
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void peekQueueMessage
            (String connectStr, String queueName)
    {
        try
        {
            // Instantiate a QueueClient which will be
            // used to create and manipulate the queue
            QueueClient queueClient = new QueueClientBuilder()
                    .connectionString(connectStr)
                    .queueName(queueName)
                    .buildClient();

            // Peek at the first message
            PeekedMessageItem peekedMessageItem = queueClient.peekMessage();
            System.out.println("Peeked message: " + peekedMessageItem.getMessageText());
        }
        catch (QueueStorageException e)
        {
            // Output the exception message and stack trace
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getQueueLength(String connectStr, String queueName)
    {
        try
        {
            // Instantiate a QueueClient which will be
            // used to create and manipulate the queue
            QueueClient queueClient = new QueueClientBuilder()
                    .connectionString(connectStr)
                    .queueName(queueName)
                    .buildClient();

            QueueProperties properties = queueClient.getProperties();
            long messageCount = properties.getApproximateMessagesCount();

            System.out.println(String.format("Queue length: %d", messageCount));
        }
        catch (QueueStorageException e)
        {
            // Output the exception message and stack trace
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void dequeueMessage(String connectStr, String queueName)
    {
        try
        {
            // Instantiate a QueueClient which will be
            // used to create and manipulate the queue
            QueueClient queueClient = new QueueClientBuilder()
                    .connectionString(connectStr)
                    .queueName(queueName)
                    .buildClient();

            // Get the first queue message
            QueueMessageItem message = queueClient.receiveMessage();

            // Check for a specific string
            if (null != message)
            {
                System.out.println("Dequeing message: " + message.getMessageText());

                // Delete the message
                queueClient.deleteMessage(message.getMessageId(), message.getPopReceipt());
            }
            else
            {
                System.out.println("No visible messages in queue");
            }
        }
        catch (QueueStorageException e)
        {
            // Output the exception message and stack trace
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void dequeueMessages2(String connectStr, String queueName)
    {
        try
        {
            // Instantiate a QueueClient which will be
            // used to create and manipulate the queue
            QueueClient queueClient = new QueueClientBuilder()
                    .connectionString(connectStr)
                    .queueName(queueName)
                    .buildClient();

            // The maximum number of messages to retrieve is 20
            final int MAX_MESSAGES = 20;

            // Retrieve 20 messages from the queue with a
            // visibility timeout of 300 seconds (5 minutes)
            for (QueueMessageItem message : queueClient.receiveMessages(MAX_MESSAGES,
                    Duration.ofSeconds(300), Duration.ofSeconds(1), new Context("key1", "value1")))
            {
                // Do processing for all messages in less than 5 minutes,
                // deleting each message after processing.
                System.out.println("Dequeing message: " + message.getMessageText());
                queueClient.deleteMessage(message.getMessageId(), message.getPopReceipt());
            }
        }
        catch (QueueStorageException e)
        {
            // Output the exception message and stack trace
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
