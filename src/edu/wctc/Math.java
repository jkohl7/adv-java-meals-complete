package edu.wctc;

import java.util.ArrayList;

public class Math {
    //created the variables that are needed for the final chart
    private int total;
    private int mean;
    private int min;
    private int max;
    private int median;

    //sets up for the calculations
    private ArrayList<Integer> calc = new ArrayList<Integer>();

    public void addValue(int calories){
        calc.add(calories);
        calculations();
    }

    //stats the calculations
    private void calculations(){
        total = 0;
        min = -1;
        max = -1;
        for(int i : calc){
            total += i;
            if(min ==- 1)min=i;
            else if(i < min) min = i;
            if(max ==-1 )max=i;
            else if(max < i) max = i;
        }

        //finding the mean
        mean = total/calc.size();
        //finding the median
        median = calc.get((calc.size()-1)/2); //maybe -1 for index?
    }

    public int getTotal() {
        return total;
    }

    public int getMean() {
        return mean;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getMedian() {
        return median;
    }
}