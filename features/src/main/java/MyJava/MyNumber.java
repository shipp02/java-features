package MyJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MyNumber implements Comparable{
    private int num;
    // private double d;

    public MyNumber(int n){
        this.num = n;
    }

    // private MyNumber(double d){
    //     this.d = d;
    // }

    public int getNum(){return this.num;}

    // public double getD(){return this.d;}

    public String toString(){
        return ""+this.num;
    }

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<MyNumber> arr = new ArrayList<>();
        arr.add(new MyNumber(random.nextInt(800)));
        arr.add(new MyNumber(random.nextInt(800)));
        arr.add(new MyNumber(random.nextInt(800)));
        arr.add(new MyNumber(random.nextInt(800)));
        arr.add(new MyNumber(random.nextInt(800)));
        
        ArrayList<MyNumber> clone = (ArrayList<MyNumber>) arr.clone();

        Collections.sort(arr, new intCompare());
        System.out.println(arr);

        Collections.sort(clone);
        System.out.println(clone);

    }

    @Override
    public int compareTo(Object o) {
        MyNumber m = (MyNumber) o;
        return this.num - m.getNum();
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