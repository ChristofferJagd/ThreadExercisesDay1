/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExerciseOne;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Christoffer
 */
public class Multithreading extends Thread {

    public static final int MAX_NUMBERS = 100000;
    public static final int TIME = 2000;
    public static volatile boolean finished = false;
    public static Timer timer = new Timer();
    
    
    
    public static class Task1 extends Thread{
        
        public void run(){
            long number = 0;
        for (int i = 1; i <= 1000000000; i++) {
            
            number += i;
        }
            System.out.println("Task1: " + number);
        }
    }
    public static class Task2 extends Thread {
        public void run(){
            try {
                for (int i = 1; i < 6; i++) {
                System.out.println(i);
                sleep(2000);
            }
            }catch (InterruptedException e){
                System.err.println("e");
            }
        }
    }
    
    public static class Task3 extends Thread {
        int number = 10;
        public static volatile boolean running = true;
        public void run(){
          try {
              while (running) {
                  System.out.println("Task3: " + number);
                  number++;
                  sleep(3000);}
              
              } catch (InterruptedException e) {
                  
          }
            
    }
    public static void main(String[] args) {
            
           try {
           new Task1().start();
           new Task2().start();
           new Task3().start();
           sleep(10000);
           Task3.running = false;
           } catch (InterruptedException e) {
               System.err.println("e");
       }
    }
    
}
}
