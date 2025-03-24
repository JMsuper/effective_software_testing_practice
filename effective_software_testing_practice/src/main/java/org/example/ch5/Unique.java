package org.example.ch5;

import java.util.Iterator;
import java.util.TreeSet;

public class Unique {
    public static int[] unique(int[] data){
        TreeSet<Integer> values = new TreeSet<>();
        for(int i = 0; i < data.length; i++){
            values.add(data[i]);
        }

        final int count = values.size();
        final int[] out = new int[count];

        Iterator<Integer> iterator = values.iterator();
        int i = 0;
        while (iterator.hasNext()){
            out[count - ++i] = iterator.next();
        }
        return out;
    }
}
