package org.apache.rocketmq.client.consumer;

import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueByMachineRoom;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AllocateMessageQueueStrategyTest {

    private AllocateMessageQueueStrategy allocateMessageQueueStrategy = new AllocateMessageQueueByMachineRoom();

    @Test
    public void allocate() {
        List<String> cidAll = new ArrayList<>();

        int queueSize = 6, consumerSize = 4;

        for (int i = 0; i < consumerSize; ++i) {
            cidAll.add(String.valueOf(i));
        }
        List<MessageQueue> messageQueues = new ArrayList<>();
        for (int i = 0; i < queueSize; ++i) {
            MessageQueue messageQueue = new MessageQueue();
            messageQueue.setQueueId(i);
            messageQueues.add(messageQueue);
        }

        for (int i = 0; i < consumerSize; ++i) {
            List<MessageQueue> res = allocateMessageQueueStrategy.allocate("group1", String.valueOf(i), messageQueues, cidAll);
            List<Integer> queueIds = new ArrayList<>();
            for (int j = 0; j < res.size(); ++j) {
                queueIds.add(res.get(j).getQueueId());
            }
            System.out.println("cid: " + i + " ,queues:" + queueIds);
        }

    }

}