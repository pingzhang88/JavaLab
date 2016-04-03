package Asg1ZeNBug;

/**
 * To recall and practice the OOP concepts introduced in Programming-II The
 * class build a blue print of a ZeNBug with basic characters:age, gender,
 * poisonous and symbol proving methods to compare(equals()), output(toString())
 * and draw different track patterns of each Bugs.
 *
 * Date: August 28, 2015
 *
 * @author Ping Zhang
 *
 */
import java.util.Random;
import static java.lang.System.*;

public class ZeNBug {

    protected static final char FEMALE = 'F';
    protected static final char MALE = 'M';
    private static final String genderCharSet = "FfMm";
    private static final String SPACE_SYMBOL = " ";

    private int age = 1;

    private char gender;

    private boolean poisonous;

    /**
     * The symbol this ZeNBug uses for drawing itself
     */
    private char symbol;

    /**
     * Default constructor which instantiate the ZeNBug fields by Random Numbers
     */
    public ZeNBug() {
        Random r = new Random();
        gender = (r.nextInt(2) == 1) ? MALE : FEMALE;
        poisonous = (r.nextInt(2) == 1);
        symbol = '*';
    }

    /**
     * Normal constructor that instantiate the ZeNBug fields by accepting
     * gender,poisonous, symbol value
     */
    public ZeNBug(char gender, boolean poisonous, char symbol) {
        if (!genderCharSet.contains("" + gender)) {
            out.println("Invalid Input, gender can only be F M f m.");
            this.gender = FEMALE;
        }
        this.gender = gender;
        this.poisonous = poisonous;
        this.symbol = symbol;
    }

    /**
     * Copy constructor that copy a ZeNBug instance
     */
    public ZeNBug(ZeNBug aBug) {
        if (aBug == null) {
            out.println("Invalid Input, ZeNBug can not be null. \n "
                    + "aBug is set to Default ZeNBug.");
            aBug = new ZeNBug();
        }
        this.age = aBug.age;
        this.gender = aBug.gender;
        this.poisonous = aBug.poisonous;
        this.symbol = aBug.symbol;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public boolean getPoisonous() {
        return poisonous;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * A mutator method that reverses the poisonous property.
     */
    public void reversePoisonous() {
        poisonous = !poisonous;
    }

    /**
     * A mutator method that increases the age property of the invoking object.
     */
    public void growUp(int n) {
        if (n <= 0) {
            out.println("Invalid Input, Please input positive integer.");
            return;
        }
        age += n;
    }

    /**
     * An override of the toString() method
     *
     * @return
     */
    @Override
    public String toString() {
        return "This is a " + age + "-year old "
                + ((poisonous) ? "Poisonous" : "Non-Poisonous") + " "
                + ((gender == FEMALE) ? "Female" : "Male")
                + " ZeNBug";
    }

    /**
     * An override of the equals method that compares two ZeNBug objects for
     * equality
     *
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) { // self-comparison
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZeNBug other = (ZeNBug) obj; //final

        if (this.age != other.age) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        if (this.poisonous != other.poisonous) {
            return false;
        }
        if (this.symbol != other.symbol) {
            return false;
        }
        return true;
        /*
         return this.symbol == other.symbol && 
         this.poisonous == other.poisonous &&
         this.age == other.age &&  
         this.gender == other.gender;
         */
    }

    /**
     * A user friendly method that draws the track pattern of the invoking
     * ZeNBug object
     */
    public void draw() {
        if (age < 3) {
            drawBaby();
            return;
        }
        if (gender == FEMALE) {
            if (poisonous == true) {
                drawZ();
            } else {
                drawN();
            }
        } else {
            if (poisonous == true) {
                drawZR();
            } else {
                drawNR();
            }

        }
    }

    /**
     * First Draw the pattern of baby bugs less than 3 years old.
     */
    public void drawBaby() {
        for (int rowC = 0; rowC < age; ++rowC) {
            for (int columnC = 0; columnC < age; ++columnC) {
                out.print(symbol);
            }
            out.println();
        }
    }

    /**
     * Draw the pattern N
     */
    public void drawN() {
        for (int rowC = 0; rowC < age; ++rowC) {
            for (int columnC = 0; columnC < age; ++columnC) {
                if (columnC == 0 || columnC == age - 1 || columnC == rowC) {
                    out.print(symbol);
                } else {
                    out.print(SPACE_SYMBOL);
                }
            }
            out.println();
        }
    }

    /**
     * Draw the pattern NR
     */
    public void drawNR() {
        for (int rowC = 0; rowC < age; ++rowC) {
            for (int columnC = 0; columnC < age; ++columnC) {
                // always print a symbol at the left (columns 0 ) and right (age - 1) columns
                // Also, always print the middle symbol at column (age - rowC - 1)
                if (columnC == 0 || // first column
                        columnC == age - 1 || // last column
                        columnC == age - rowC - 1) // middle column
                {
                    out.print(symbol);
                } else {
                    out.print(SPACE_SYMBOL);
                }
            }
            out.println();
        }
    }

    /**
     * Draw the pattern Z
     */
    public void drawZ() {
        for (int rowC = 0; rowC < age; ++rowC) {
            for (int columnC = 0; columnC < age; ++columnC) {
                if (rowC == 0 || rowC == age - 1 || rowC == age - columnC - 1) {
                    out.print(symbol);
                } else {
                    out.print(SPACE_SYMBOL);
                }
            }
            out.println();
        }
    }

    /**
     * Draw the pattern ZR
     */
    public void drawZR() {
        for (int rowC = 0; rowC < age; ++rowC) {
            for (int columnC = 0; columnC < age; ++columnC) {
                if (rowC == 0 || rowC == age - 1 || rowC == columnC) {
                    out.print(symbol);
                } else {
                    out.print(SPACE_SYMBOL);
                }
            }
            out.println();
        }
    }
}
