/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list6;

import java.util.ArrayList;

/**
 *
 * @author MJOdorczuk
 */
public class Filter extends Thread{
    
    private final Buffor<String> buffor;
    private final ArrayList<String> toFilter;
    
    public Filter(Buffor<String> buffor)
    {
        this.buffor = buffor;
        this.buffor.addProvider(this);
        this.toFilter = new ArrayList<>();
    }
    
    public String filter(String s)
    {
        if(s.contains("2"))
            return null;
        else return s;
    }
    
    public void toFilter(ArrayList<String> toFilter)
    {
        this.toFilter.addAll(toFilter);
    }
    
    @Override
    public void run()
    {
        String s;
        while(!this.toFilter.isEmpty())
        {
            s = this.filter(this.toFilter.remove(0));
            if(s!=null)
                this.buffor.push(s);
        }
    }
}
