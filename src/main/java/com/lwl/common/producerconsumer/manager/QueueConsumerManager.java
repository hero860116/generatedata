package com.lwl.common.producerconsumer.manager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.lwl.common.producerconsumer.Queuetask.IQueueTask;
import com.lwl.common.producerconsumer.thread.SingleConsumerThread;

/**
 * ���У������ߣ������� ����
 * @author liweilin
 *
 */
public class QueueConsumerManager
{
  
    /**
     * ��������
     */
    private BlockingQueue<IQueueTask> blockingQueue;
    
    /**
     * ����������߳�
     * 
     * @param name       ģʽ����
     * @param queueSize  ���д�С
     * @param threadSize �̸߳���
     */
    public QueueConsumerManager(String name, int queueSize, int threadSize)
    {
        //����ָ����С����������
        blockingQueue = new ArrayBlockingQueue<IQueueTask>(queueSize);
        
        //�����������߳�
        for (int i = 1; i <= threadSize; i++)
        {
        	 new SingleConsumerThread(name + i, blockingQueue).start();
        }
    }
    
   
    
    /**
     * ������������task
     * @param queueTask ��������
     * @param taskName  ��������
     */
    public void put(IQueueTask task)
    {
        try
        {
            //��������
            blockingQueue.put(task);
        }
        catch (InterruptedException e)
        {

        }
    } 
    
    /**
     * ��ȡ�����������߶��д�С
     * 
     * @return ���д�С
     */
    public int getSize()
    {
        return blockingQueue.size();
    }

}