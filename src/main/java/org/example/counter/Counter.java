package org.example.counter;

public class Counter implements Runnable{
    private int count = 0;
    public void increment(){
        count++;
    }

    public void decrement(){
        count--;
    }

    public int getValue(){
        return count;
    }

    @Override
    public void run(){
        //동기화
        synchronized (this){
            this.increment();
            System.out.println("Vlaue for Thread After increament " + Thread.currentThread().getName() + " " + this.getValue()); //1
            this.decrement();
            System.out.println("Value for Thread at last " + Thread.currentThread().getName() + " " + this.getValue()); //0
        }


    }
}
