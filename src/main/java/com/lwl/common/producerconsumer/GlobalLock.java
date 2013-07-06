package com.lwl.common.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �ϳɶ���ȫ����
 * 
 * @author liweilin
 *
 */
public final class GlobalLock
{
    /**
     * ����
     */
    private static GlobalLock instance = new GlobalLock();
    
    /**
     * ��
     */
    private ReentrantLock assignerLock;

    /**
     * �ȴ���Ϊ��
     */
    private Condition assignerNotEmpty;
    
    /**
     * ˽�й��캯��
     *
     */
    private GlobalLock()
    {
        assignerLock = new ReentrantLock();
        assignerNotEmpty = assignerLock.newCondition();
    }
    
    /**
     * ���ȫ����
     * @return
     */
    public static ReentrantLock getReentrantLock()
    {
        return instance.getAssignerLock();
    }
    
    /**
     * ��õȴ�����
     * @return
     */
    public static Condition getCondition()
    {
        return instance.getAssignerNotEmpty();
    }

    public ReentrantLock getAssignerLock()
    {
        return assignerLock;
    }

    public Condition getAssignerNotEmpty()
    {
        return assignerNotEmpty;
    }
   
}
