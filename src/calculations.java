public class calculations {
    //calculations goes here

    public double pe;
    public double add;

    public calculations() {
        formula(0,0,0,0,0);
        addOn(0,0,0,0);

    }


    public double formula(double p, double r, double n, double downPayment, double op) {
        double principle = p - downPayment;
        double rate = (r / 100) / 12;
        double times = n * 12;

        pe = (principle * rate) / (1-Math.pow(1+rate, -times));
        return pe + op;
    }

    public double addOn(double pTax, double iS, double PMI, double HOA) {
         add = pTax + iS + PMI + HOA;
         return add;



    }




}
