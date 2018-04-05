/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list6;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MJOdorczuk
 * @param <T>
 */
public class Buffor<T>{
    
    private final ConcurrentLinkedQueue<T> buffor;
    private final int maxSize;
    private final ArrayList<Thread> providers;
    private final ArrayList<Thread> collectors;
    
    public Buffor(int maxSize)
    {
        this.buffor = new ConcurrentLinkedQueue<>();
        if(maxSize > 0)
            this.maxSize = maxSize;
        else
        {
            this.maxSize = 0;
        }
        this.providers = new ArrayList<>();
        this.collectors = new ArrayList<>();
    }
    
    public synchronized void push(T element) {
        while(buffor.size() == maxSize)
        {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffor.add(element);
        notify();
    }
    
    public synchronized T pull(){
        if(buffor.isEmpty())
            try {
                wait(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffor.class.getName()).log(Level.SEVERE, null, ex);
        }
        notify();
        if(buffor.isEmpty())
        {
            return null;
        }
        else return this.buffor.poll();
    }
    
    public int maxSize(){
        return this.maxSize;
    }
    
    public synchronized boolean isFull(){
        return this.buffor.size() >= maxSize;
    }
    
    public synchronized boolean isEmpty(){
        return this.buffor.isEmpty();
    }
    
    public synchronized void addProvider(Thread provider){
        this.providers.add(provider);
    }
    
    public synchronized void addCollector(Thread collector){
        this.collectors.add(collector);
    }
    
    public synchronized void removeProvider(Thread provider){
        this.providers.remove(provider);
    }
    
    public synchronized void removeCollector(Thread collector){
        this.collectors.remove(collector);
    }
    
    public synchronized boolean noMore()
    {
        boolean ret = true;
        for (Thread provider : providers) {
            ret = ret && provider.isAlive();
        }
        return !ret;
    }
}
