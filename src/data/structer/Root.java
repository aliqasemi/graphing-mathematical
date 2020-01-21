
package data.structer;

public class Root {
    double firstRange ;
    double lastRange ;
    String expr ;

    public Root(double firstRange, double lastRange, String expr) {
        this.firstRange = firstRange;
        this.lastRange = lastRange;
        this.expr = expr;
    }
    public double evalue(){
        double result ;
        Pos x = new Pos() ;
        x.setinfix(this.expr) ;
        x.infixToPostfix() ;
        Derivative y = new Derivative(expr);
        double arr [] = new double[10];
        arr[0] = ( this.firstRange + this.lastRange ) / 2 ;
        for (int i = 1 ; i < 10 ; i ++ ) {
            x.setInfix(this.expr.replaceAll("x" , "" + arr[i - 1])) ;
            x.infixToPostfix() ;
            arr[i] = arr [ i - 1 ] - (x.evaluatePostfix()/y.evalue(arr [ i - 1 ]));
        }
        result = arr[9];
        return result ;
    }
}

