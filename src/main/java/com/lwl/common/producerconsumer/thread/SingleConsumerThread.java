package com.lwl.common.producerconsumer.thread;

import java.util.concurrent.BlockingQueue;

import com.lwl.common.producerconsumer.Queuetask.IQueueTask;

/**
 * �򵥶����������߳�
 * @author liweilin
 *
 */
public class SingleConsumerThread extends Thread
{
    /**
     * ���ݶ���
     */
    private BlockingQueue<IQueueTask> blockingQueue;
    
    /**
     * ���췽��
     * @param name             �߳�����
     * @param blockingQueue    ����
     */
    public SingleConsumerThread(String name, BlockingQueue<IQueueTask> blockingQueue)
    {
        super(name);
        
        this.blockingQueue = blockingQueue;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                IQueueTask task = blockingQueue.take();
                
                task.execute();
                
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}
