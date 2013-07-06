
package com.lwl.common.producerconsumer.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ֧�ֻ����Ķ���
 * @author liweilin
 *
 * @param <T>
 */
public class MyQueue<T>
{
    
    private LinkedList<T> queue = new LinkedList<T>();

    private int maxSize;

    //��
    private final ReentrantLock lock;
    
    //����
    private final Condition notFull;;

    /**
     * ��ʼ������
     * @param size ��С
     */
    public MyQueue(int size)
    {
        maxSize = size;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
    }

    /**
     * ����������Ԫ��
     * @param e
     */
    public void addFirst(T e)
    {
        queue.addFirst(e);
    }

    /**
     * ����������Ԫ��
     * @param e
     * @throws InterruptedException
     */
    public void put(T e) throws InterruptedException
    {
        lock.lock();
        try
        {
            while (queue.size() == maxSize)
            {
                notFull.await();
            }

            queue.addLast(e);
        }
        finally
        {
            lock.unlock();
        }
    }

    
    /**
     * ��������ȡ����
     * @return
     */
    public T take()
    {
        lock.lock();

        T task = null;
        try
        {
            task = queue.removeFirst();
            notFull.signalAll();
        }
        finally
        {
            lock.unlock();
        }

        return task;
    }

    /**
     * �ж϶����Ƿ�Ϊ��
     * @return
     */
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    
    /**
     * ��ö��д�С
     * @return
     */
    public int size()
    {
        return queue.size();
    }

}
