//Joel Ye
//APCS1 pd10
//HW45 -- Come Together
//2015 - 12 - 09

public class Rational implements Comparable {

    private int num, den;

    // CONSTRUCTORS
    // ===========================================
    //default constructor
    public Rational(){
	num = 0;
	den = 1;
    }
	
    //overloaded constructor: checks for division by 0
    public Rational(int n, int d){
	this();
	if (d == 0)
	    System.out.print ("MathError: Division by 0.");
	else {
	    num = n;
	    den = d;
	}
    }

    // METHODS
    // ===========================================
	
    //prints rational number with newlines
    public String toString(){
	return num + "/" + den;
    }

    
    //float approximation of rational number
    public float floatValue(){
		return (float)(num)/(den);
	//Casting has precedence to division.
    }

	public float decValue(){
		return floatValue();
	}
    
    //multiplication
    public void multiply (Rational n){
        num *= n.num;
	den *= n.den;
	//Needn't worry about division by 0, param is also rational.
    }
    
	
    //division
    public void divide(Rational n){
	if (n.num == 0){
	    System.out.println("MathError: Division by 0");
	    return;
	}
	num *= n.den;
	den *= n.num;
    }


    
    
    // HW33 ADDITIONS
    // ===========================================

    // calc gcd of two ints
    public static int gcd(int a, int b){
	// deal with negatives
		a = Math.abs(a);
		b = Math.abs(b);
		
        while (b != 0) {
            int num = b;
            b = a % b;
            a = num;
        }
        return a;
    }


    // return gcd of numerator and denominator
    public int gcd(){
	return gcd(num,den);
    }


    // simplify the numerator and denominator of a Rational object
    public static void reduce(Rational n){
	int gcd = gcd(n.num, n.den);
	n.num /= gcd;
	n.den /= gcd;
	if(n.den < 0){
		n.num *= -1;
		n.den *= -1;
		//How in the world is it so compact? Well, if den (and num) is (are) < 0
		//This will make n all positive. If only den is less than 0, sign flips to numerator.
		//If both are positive, w/e.
	}
    }


    // add to another Rational object
    public void add(Rational a){
	num = num * a.den + den * a.num;
	den *= a.den;
	reduce(this);
    }


    // subtract another Rational object
    public void subtract(Rational a){
	num = num * a.den - den * a.num;
	den *= a.den;
	reduce(this);
    }


    //  returns diff of two Rational objects
    public int compareTo(Rational a){
		return num * a.den - den * a.num;
    }
	
	// returns diff of Rational and object?
	//merges equals and compareTo
	public int compareTo( Object other ) {
		if (other == null)
			throw new NullPointerException();
		ClassCastException e = new ClassCastException("Can't cast " + other.getClass() + " to a Comparable class.");
		if (other instanceof Comparable)
			return (int)(decValue() - ((Comparable)other).decValue());
		else 
			throw e;
    }
	
    public boolean equals( Object other ) { 
	return this == other || (other instanceof Comparable && decValue() == ((Comparable)other).decValue());   
    }

}
