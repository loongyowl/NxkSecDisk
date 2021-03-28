package edu.swufe.nxksecdisk.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ThreadPoolConfig
 * @Description TODO
 * @CreateTime 2021年03月28日 20:16:00
 */
public  final class ThreadPoolConfig {

    private static final int QUEUE_SIZE = 12;

    public static final int corePoolSize = 24;

    public static final int maximumPoolSize = 30;

    public static final long keepAliveTime = 60;

    public static final TimeUnit unit = TimeUnit.SECONDS;

    public static final BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(QUEUE_SIZE);

    private ThreadPoolConfig() {}
}
