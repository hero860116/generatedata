package com.lwl.common.producerconsumer.assigner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lwl.common.producerconsumer.Queuetask.IManyQueueTask;
import com.lwl.common.producerconsumer.queue.MyQueue;

/**
 * ������ʵ��
 * @author liweilin
 *
 */
public abstract class AAssigner<T>
{
    /**
     * ������ϢMap
     * ���ÿ��������ϸ��Ϣ��������������Щ��Ϣ���з����㷨����
     */
    protected Map<String, T> taskInfoMap = new ConcurrentHashMap<String, T>();
    
    /**
     * ����Map
     * ���������ݷ����㷨����Щ�����л�ȡ����
     */
    protected Map<String, MyQueue<IManyQueueTask>> taskQueueMap;
    
    /**
     * ��ȡ����ӿڣ��������̵߳���
     * @return ����
     */
    public abstract IManyQueueTask obtainTask();
    
    /**
     * ������������һ��������Ϣ
     * @param taskId
     * @param task
     */
    public void addTaskInfo(String taskId, T task)
    {
        taskInfoMap.put(taskId, task);
    }

    public void setTaskQueueMap(Map<String, MyQueue<IManyQueueTask>> taskQueueMap)
    {
        this.taskQueueMap = taskQueueMap;
    }

    public void removeTask(String taskId)
    {
        taskInfoMap.remove(taskId);
        taskQueueMap.remove(taskId);
    }
}
