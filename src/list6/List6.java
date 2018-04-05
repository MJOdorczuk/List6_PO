/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list6;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MJOdorczuk
 */
public class List6 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws InterruptedException {
        
        Buffor<String> marketplace = new Buffor<>(10);
        Buffor<String> filtered = new Buffor<>(2);
        Producer baker = new Producer(marketplace,"Baker John");
        Producer cook = new Producer(marketplace,"Cook Jacob");
        Consumer omnom = new Consumer(marketplace);
        Consumer yumyum = new Consumer(marketplace);
        Filter filter = new Filter(filtered);
        Sorter sorter = new Sorter(filtered);
        Random rnd = new Random();
        ArrayList<String> toFilter;
        toFilter = new ArrayList<>();
        for(Integer i = 0; i < 15; i++)
        {
            toFilter.add(i.toString());
        }
        System.out.println(toFilter);
        System.out.println("Filtered and sorted into:");
        filter.toFilter(toFilter);
        filter.start();
        sorter.start();
        while(sorter.isAlive());
        System.out.println(sorter.getSorted().toString());
        /*baker.start();
        cook.start();
        omnom.start();
        yumyum.start();*/
    }
    
}
