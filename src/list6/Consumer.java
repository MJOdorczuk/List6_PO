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
public class Consumer extends Thread{
    
    
    private Buffor<String> marketplace;
    
    public Consumer(Buffor<String> marketplace)
    {
        this.marketplace = marketplace;
        this.marketplace.addCollector(this);
    }
    
    public void changeMarketplace(Buffor<String> marketplace)
    {
        this.marketplace.removeCollector(this);
        this.marketplace = marketplace;
        this.marketplace.addCollector(this);
    }
    
    public void consume(String product)
    {
        //Omnomnom! Transforming tasty strings into void
        System.out.print(product + "\n");
    }
    
    @Override
    public void run()
    {
        Random rnd = new Random();
        while(true)
        {
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.consume(this.marketplace.pull());
        }
    }
}
