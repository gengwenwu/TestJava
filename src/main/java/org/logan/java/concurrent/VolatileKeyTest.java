package org.logan.java.concurrent;

/**
 * desc: volatile关键字 案例解析 <br/>
 * 参考：
 * 	https://baijiahao.baidu.com/s?id=1647517428492944251
 *
 *
 * time: 2020/6/18 4:45 下午 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */

class VolatileKeyTest {

	// 在Java多线程的开发中有三种特性：原子性、可见性和有序性。
	// volatile关键字使用场景：
	// 	  场景一，有序性：为了防止上面的重排序。
	//    场景二，可见性：它标记一个变量，在多个线程访问它的时候，总是转到主内存，读取和写入。




	/**
	 * 场景一，有序性，解析。
	 * 程序在运行的时候一定会先让 i=1 ，然后 j=2吗？
	 * 不一定，因为有可能会发生指令重排序。volatile可以确保这种想象不会发生。
	 **/
	int i = 1;int j = 2;


	/**
	 *  场景二，可见性，解析
	 **/
	private static volatile int a = 0; // 使用 volatile 关键字


	public static void main(String[] args) {
		showVolatileOnNonAtomic();
		// showVolatileUseSynchronized();
	}

	/**
	 *
	 * volatile 关键字虽然保证了 可见性和有序性，但是它可不管原子性。
	 * 下面是错误的案例
	 */
	private static void showVolatileOnNonAtomic() {
		Thread[] threads = new Thread[5];

		for (int i = 0; i < 5; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10; j++) {
					// 最终的结果不是50，而且出现重复数据。
					System.out.println(++a);
					sleep();
				}
			});

			threads[i].start();
		}
	}

	/**
	 * 针对 {@link #showVolatileOnNonAtomic()} 优化
	 * 正确做法：确保原子性质
	 */
	private static void showVolatileUseSynchronized() {
		Thread[] threads = new Thread[5];

		for (int i = 0; i < 5; i++) {
			threads[i] = new Thread(() -> {

				for (int j = 0; j < 10; j++) {
					synchronized (VolatileKeyTest.class) { // synchronized，确保原子性质
						System.out.println(++a);
					}

					sleep();
				}
			});

			threads[i].start();
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
