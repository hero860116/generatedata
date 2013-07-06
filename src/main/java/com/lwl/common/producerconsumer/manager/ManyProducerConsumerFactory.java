package com.lwl.common.producerconsumer.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �����������߹���
 * 
 * @author liweilin
 *
 */
public class ManyProducerConsumerFactory
{
    /**
     * ����
     */
    private static ManyProducerConsumerFactory instance = new ManyProducerConsumerFactory();
    
    /**
     * ����Ѿ�����õ�������������ģ��
     */
    private Map<String, ManyQueueConsumerManager> queueConsumerManagerMap = new ConcurrentHashMap<String, ManyQueueConsumerManager>();
    
    /**
     * ˽�й��췽��
     *
     */
    private ManyProducerConsumerFactory()
    {
        
    }
    
    /**
     * ����������������ģ��
     * 
     * @param name       ����������������
     * @param queueSize  ���д�С
     * @param threadSize �������̸߳���
     */
    public static void createProducerConsumer(String name, Class assigner, int threadSize)
    {
        //����������������ģ��
        ManyQueueConsumerManager queueConsumerManager = new ManyQueueConsumerManager(assigner, threadSize, name);
        
        //���뻺��
        instance.getQueueConsumerManagerMap().put(name, queueConsumerManager);
    }
    
    /**
     * ���������������߹�����
     * 
     * @param name ����������������
     * @return �����������߹�����
     */
    public static ManyQueueConsumerManager getProducerConsumer(String name)
    {
        return instance.getQueueConsumerManagerMap().get(name);
    }

    private Map<String, ManyQueueConsumerManager> getQueueConsumerManagerMap()
    {
        return queueConsumerManagerMap;
    }
    
}