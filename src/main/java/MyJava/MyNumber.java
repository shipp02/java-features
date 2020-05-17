package MyJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyNumber {
    private int num;
    private double d;

    public MyNumber(int n){
        this.num = n;
    }

    private MyNumber(double d){
        this.d = d;
    }

    public int getNum(){return this.num;}

    public double getD(){return this.d;}

    public String toString(){
        return ""+this.num;
    }

    public static void main(String[] args) {
        ArrayList<MyNumber> arr = new ArrayList<>();
        arr.add(new MyNumber(1));
        arr.add(new MyNumber(-20));
        arr.add(new MyNumber(60));
        arr.add(new MyNumber(-100));

        Collections.sort(arr, new intCompare());
        System.out.print(arr);

    }
}

class intCompare implements Comparator<MyNumber> {

    @Override
    public int compare(MyNumber o1, MyNumber o2) {
        int val;
        val = o1.getNum() - o2.getNum();

        return val;
    }    
}