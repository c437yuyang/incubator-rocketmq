package org.apache.rocketmq.client.consumer.rebalance;

import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AllocateMessageQueueAveragelyByCircleTest {

    private AllocateMessageQueueAveragelyByCircle allocateMessageQueueAveragelyByCircle = new AllocateMessageQueueAveragelyByCircle();

    @Test
    public void allocate() {
        List<String> cidAll = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            cidAll.add(String.valueOf(i));
        }
        List<MessageQueue> messageQueues = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            MessageQueue messageQueue = new MessageQueue();
            messageQueue.setQueueId(i);
            messageQueues.add(messageQueue);
        }

        for (int i = 0; i < 10; ++i) {
            List<MessageQueue> res = allocateMessageQueueAveragelyByCircle.allocate("group1", String.valueOf(i), messageQueues, cidAll);
            List<Integer> queueIds = new ArrayList<>();
            for (int j = 0; j < res.size(); ++j) {
                queueIds.add(res.get(j).getQueueId());
            }
            System.out.println("cid: " + i + " ,queues:" + queueIds);
        }

    }
}