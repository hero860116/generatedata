
package com.lwl.common.producerconsumer.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lwl.common.producerconsumer.GlobalLock;
import com.lwl.common.producerconsumer.Queuetask.IManyQueueTask;
import com.lwl.common.producerconsumer.assigner.AAssigner;
import com.lwl.common.producerconsumer.queue.MyQueue;
import com.lwl.common.producerconsumer.thread.ConsumerThread;

/**
 * ���У������ߣ������� ����
 * @author liweilin
 *
 */
public class ManyQueueConsumerManager
{
    /**
     * ����Map
     */
    private Map<String, MyQueue<IManyQueueTask>> taskQueueMap = new ConcurrentHashMap<String, MyQueue<IManyQueueTask>>();

    /**
     * ������
     */
    private AAssigner assigner;

    /**
     * ����������������
     */
    private String name;

    /**
     * �߳��б�
     */
    private List<ConsumerThread> consumerThreads = new LinkedList<ConsumerThread>();

    /**
     * 
     * @param queueSize
     * @param threadSize
     */
    public ManyQueueConsumerManager(Class assignerclass, int threadSize, String name)
    {
        try
        {
            this.name = name;
            assigner = (AAssigner) assignerclass.newInstance();
            assigner.setTaskQueueMap(taskQueueMap);
        }
        catch (InstantiationException e)
        {
        }
        catch (IllegalAccessException e)
        {
        }

        for (int i = 1; i <= threadSize; i++)
        {
            ConsumerThread cThread = new ConsumerThread(name + i, assigner);
            cThread.start();

            consumerThreads.add(cThread);
        }
    }

    /**
     * ������������task
     * @param queueTask ��������
     * @param taskName  ��������
     */
    public void putQueueTask(String taskId , IManyQueueTask task)
    {
        //���������ָ������
        try
        {
            taskQueueMap.get(taskId).put(task);
        }
        catch (InterruptedException e)
        {
        }

        //�����������߳�
        try
        {
            GlobalLock.getReentrantLock().lock();
            GlobalLock.getCondition().signalAll();
        }
        finally
        {
            GlobalLock.getReentrantLock().unlock();
        }
    }
    
    /**
     * �������ײ�����task
     * @param queueTask ��������
     * @param taskName  ��������
     */
    public void putFirstQueueTask(String taskId, IManyQueueTask queueTask)
    {
        //��������ף�����ʹ��
        taskQueueMap.get(taskId).addFirst(queueTask);
        
        //�����������߳�
        try
        {
            GlobalLock.getReentrantLock().lock();
            GlobalLock.getCondition().signalAll();
        }
        finally
        {
            GlobalLock.getReentrantLock().unlock();
        }
    }

    /**
     * ��������������task
     * @param <T>
     * @param taskId    ����ID
     * @param size      ���д�С
     * @param taskInfo  ������Ϣ
     */
    public <T> void addNewTask(String taskId , int size , T taskInfo)
    {

        GlobalLock.getReentrantLock().lock();

        try
        {
            //���Ӷ���
            taskQueueMap.put(taskId, new MyQueue<IManyQueueTask>(size));
            
            //����������Ϣ
            assigner.addTaskInfo(taskId, taskInfo);

        }
        finally
        {
            GlobalLock.getReentrantLock().unlock();
        }
    }

    /**
     * �����߳�����
     *
     */
    public void refluenceThreadTask()
    {
        //ѭ�������߳�
        for (ConsumerThread consumerThread : consumerThreads)
        {
            //��ȡ�̵߳�ǰ���ڴ��������
            IManyQueueTask queueTask = consumerThread.getQueueTask();
            
            //�������������Ϊ��
            if (queueTask != null)
            {
                //ִ�л���
                queueTask.refluenceTask();
            }
        }
    }

    
    public AAssigner getAssigner()
    {
        return assigner;
    }

    public String getName()
    {
        return name;
    }
}
