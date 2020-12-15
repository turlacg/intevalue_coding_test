package com.intevalue.multithreading;

public class ProducerConsumer {
	static Object key = new Object();
    private static boolean[] buffer;
    private static int currentSize;

    public static void main(String[] args) throws InterruptedException {
        
    	buffer = new boolean[10];
        currentSize = 0;

        final Producer producer = new Producer();
        final Consumer consumer = new Consumer();

        Runnable r1 = new Runnable() {
            public void run() {
                for (int x = 0; x < 100; x++) {
                    producer.produce();
                }
                System.out.println("thread > Produce");
            }
        };

        Runnable r2 = new Runnable() {
            public void run() {
                for (int x = 0; x < 100; x++) {
                    consumer.consume();
                }
                System.out.println("thread > Consume");
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Buffer size : " + currentSize);
    }

    static class Producer {
        void produce() {
            synchronized (key) {
                if (currentSize == buffer.length) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[currentSize++] = true;
                key.notifyAll();
            }
        }
    }

    static class Consumer {
        void consume() {
            synchronized (key) {
                if (currentSize == 0) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[--currentSize] = false;
                key.notifyAll();
            }
        }
    }
}
