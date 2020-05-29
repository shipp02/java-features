package spring;

public class Address {
    String line1 = null;
    String line2 = null;

    public Address(String l1, String l2){
        this.line1 = l1;
        this.line2 = l2;
    }

    @Override
    public String toString(){
        return this.line1+ "\n" + this.line2;
    }

    public void setLine1(String l){
        this.line1 = l;
    }

    public void setLine2(String l){
        this.line2 = l;
    }

    public String getLine1(){
        return this.line1;
    }

    public String getLine2(){
        return this.line2;
    }
}