package com.lwl.common.producerconsumer.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * �����������߹���
 * 
 * @author liweilin
 *
 */
public class ProducerConsumerFactory
{
    /**
     * ����
     */
    private static ProducerConsumerFactory instance = new ProducerConsumerFactory();
    
    /**
     * ����Ѿ�����õ�������������ģ��
     */
    private Map<String, QueueConsumerManager> queueConsumerManagerMap = new ConcurrentHashMap<String, QueueConsumerManager>();
    
    /**
     * ˽�й��췽��
     *
     */
    private ProducerConsumerFactory()
    {
        
    }
    
    /**
     * ����������������ģ��
     * 
     * @param name       ����������������
     * @param queueSize  ���д�С
     * @param threadSize �������̸߳���
     */
    public static void createProducerConsumer(String name, int queueSize, int threadSize)
    {
        //����������������ģ��
        QueueConsumerManager queueConsumerManager = new QueueConsumerManager(name, queueSize, threadSize);
        
        //���뻺��
        instance.getQueueConsumerManagerMap().put(name, queueConsumerManager);
    }
    
    /**
     * ���������������߹�����
     * 
     * @param name ����������������
     * @return �����������߹�����
     */
    public static QueueConsumerManager getProducerConsumer(String name)
    {
        return instance.getQueueConsumerManagerMap().get(name);
    }

    private Map<String, QueueConsumerManager> getQueueConsumerManagerMap()
    {
        return queueConsumerManagerMap;
    }
    
}
