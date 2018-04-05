/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MJOdorczuk
 */
public class Producer extends Thread{

    private Buffor<String> marketplace;
    private final String name;
    private int counter;
    
    public Producer(Buffor<String> marketplace, String name)
    {
        this.marketplace = marketplace;
        this.marketplace.addProvider(this);
        this.name = name;
        this.counter = 0;
    }
    
    public void changeMarketplace(Buffor<String> marketplace)
    {
        this.marketplace.removeProvider(this);
        this.marketplace = marketplace;
        this.marketplace.addProvider(this);
        this.counter = 0;
    }
    
    public String produce()
    {
        System.out.println("Produced " + counter + name);
        return "Vollo illo!" + counter++ + " produced by " + name;
    }
    
    public void retire()
    {
        this.marketplace.removeProvider(this);
        this.marketplace = null;
    }
    
    @Override
    public void run() {
        Random rnd = new Random();
        while(true)
        {
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.marketplace.push(this.produce());
        }
    }
    
}
