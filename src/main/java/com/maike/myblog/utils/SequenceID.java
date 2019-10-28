package com.maike.myblog.utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectname BeautyDress
 * @author GEEKCJJ
 * @date 2019年8月27日 下午6:56:01
 * @description:
 *
 */
public class SequenceID {
	private static final long ONE_STEP = 10;
	private static final Lock LOCK = new ReentrantLock();
	private static long lastTime = System.currentTimeMillis();
	private static short lastCount = 0;
	private static int count = 0;

	@SuppressWarnings("finally")
	public static String nextId() {
		LOCK.lock();
		try {
			if (lastCount == ONE_STEP) {
				boolean done = false;
				while (!done) {
					long now = System.currentTimeMillis();
					if (now == lastTime) {
						try {
							Thread.currentThread();
							Thread.sleep(1);
						} catch (java.lang.InterruptedException e) {
						}
						continue;
					} else {
						lastTime = now;
						lastCount = 0;
						done = true;
					}
				}
			}
			count = lastCount++;
		} finally {
			LOCK.unlock();
			return lastTime + "" + String.format("%06d", count);
		}
	}

	// 获得batchNo批次号
	@SuppressWarnings({ "finally" })
	public static String getBatchNo() {
		LOCK.lock();
		try {
			if (lastCount == ONE_STEP) {
				boolean done = false;
				while (!done) {
					long now = System.currentTimeMillis();
					if (now == lastTime) {
						try {
							Thread.currentThread();
							Thread.sleep(1);
						} catch (java.lang.InterruptedException e) {
						}
						continue;
					} else {
						lastTime = now;
						lastCount = 0;
						done = true;
					}
				}
			}
			count = lastCount++;
		} finally {
			LOCK.unlock();
			String str = lastTime + "" + String.format("%06d", count);
			String batchNo = "C" + str.substring(1);
			return batchNo;
		}
	}

	public static void main(String[] args) {
		// 测试
		System.out.println(nextId());
		System.out.println(nextId());
	}
}
