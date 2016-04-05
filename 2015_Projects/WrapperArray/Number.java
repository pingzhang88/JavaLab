package Asg2B;

/**
 * A class that wraps an integer variable and provides operations to manipulate the
 * value of that variable.
 *
 * @author
 */
public class Number {

    //Additional for testing Copy Constructor, to make it simple, public is used.
    //public Number[] objA;
    private int value;

    public Number() {
        this.value = 0;
    }

    public Number(int value) {
        this.value = value;
    }

    public Number(Number other) {
        this.value = other.value;

        //Additional for testing Copy Constructor, 
        /*
         for(int m = 0;m<other.objA.length;++m){
         this.objA[m].value = other.objA[m].value;
         }        
         */
    }

    public int get() {
        return this.value;
    }

    public void set(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        Number n = (Number) obj;
        return this.value == n.value;
    }
}
