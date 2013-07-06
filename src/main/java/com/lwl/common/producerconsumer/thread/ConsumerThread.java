package com.lwl.common.producerconsumer.thread;

import com.lwl.common.producerconsumer.Queuetask.IManyQueueTask;
import com.lwl.common.producerconsumer.assigner.AAssigner;

/**
 * ������������߳�
 * @author liweilin
 *
 */
public class ConsumerThread extends Thread
{
    /**
     * ������
     */
    private AAssigner assigner;
    
    /**
     * ��ǰ���������
     */
    private IManyQueueTask queueTask;
    
    /**
     * ���췽��
     * 
     * @param name     �߳�����
     * @param assigner ������
     */
    public ConsumerThread(String name, AAssigner assigner)
    {
        super(name);
        this.assigner = assigner;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            //ͨ����������ȡ����
            queueTask = assigner.obtainTask();
            
            //ִ������
            queueTask.execute();
            
            //�����������ԭΪnull
            queueTask = null;
            
            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
            }
            
        }
    }

    /**
     * ��ȡ��ǰ���������
     * @return ��������
     */
    public IManyQueueTask getQueueTask()
    {
        return queueTask;
    }
}
