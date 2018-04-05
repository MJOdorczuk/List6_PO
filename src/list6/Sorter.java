/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list6;

import java.util.Vector;

/**
 *
 * @author MJOdorczuk
 */
public class Sorter extends Thread{
    
    private final Buffor<String> buffor;
    private final Vector<String> sorted;
    
    public Sorter(Buffor<String> buffor)
    {
        this.buffor = buffor;
        this.buffor.addCollector(this);
        this.sorted = new Vector<>();
    }
    
    @SuppressWarnings("empty-statement")
    public void inject(String s)
    {
        int i = 0;
        if(!this.sorted.isEmpty())
        {
            while(this.sorted.get(i).compareTo(s) < 0 && this.sorted.size() > i + 1)
                i++;
            this.sorted.add(i, s);
        }
        else
            this.sorted.add(s);
    }
    
    public Vector<String> getSorted()
    {
        return this.sorted;
    }
    
    @Override
    public void run()
    {
        String s;
        while(true)
        {
            s = this.buffor.pull();
            if(s == null) return;
            else this.inject(s);
        }
    }
}
