/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExerciseTwo;

/**
 *
 * @author Christoffer
 */
public class MultiThreadingExcercise2 extends Thread {

    public static Object lock = new Object();

    public static void main(String[] args) {
        Even even = new Even();

        //unsynced threads
        myThreadUnsync t1 = new myThreadUnsync(even);
        myThreadUnsync t2 = new myThreadUnsync(even);
        t1.start();
        t2.start();

        //synced threads
//        myThreadSynced t3 = new myThreadSynced(even);
//        myThreadSynced t4 = new myThreadSynced(even);
//        t3.start();
//        t4.start();
    }

    public static class myThreadUnsync extends Thread {

        private Even even;
        public volatile boolean isEven = true;

        public myThreadUnsync(Even even) {
            this.even = even;
        }

        @Override
        public void run() {
            int numberEven = 0;
            while (isEven) {
                if (numberEven % 2 != 0) {
                    System.out.println("Found an uneven number: " + numberEven);
                    isEven = false;
                } else {
                    numberEven = even.next();
                    
                }

            }
        }

    }

    public static class myThreadSynced extends Thread {

        private Even even;
        public volatile boolean isEven = true;

        public myThreadSynced(Even even) {
            this.even = even;
        }

        @Override
        public void run() {
            int numberEven = 0;
            synchronized (lock) {
                while (isEven) {
                    if (numberEven % 2 != 0) {
                        System.out.println("Found an uneven number: " + numberEven);
                        isEven = false;
                    } else {
                        numberEven = even.next();
                    }

                }
            }
        }
    }

    public static class Even {

        private int n = 0;

        public int next() {
            n++;
            n++;
            return n;
        }
    }
}
