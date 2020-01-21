
package data.structer;

/**
 *
 * @author User
 */
public class Derivative {
    String expr ;


    public Derivative(String expr) {
        this.expr = expr;
    }

    public  double evalue (double value ){
        Pos x = new Pos();
        Pos y = new Pos();
        String a = "" + value ;
        String aplus = ""  + ( value + 0.0000000001 ) ;
        String s1 = this.expr.replaceAll("x" , a);
        String s2 = this.expr.replaceAll("x" , aplus);
        x.setInfix(s1);
        x.infixToPostfix();
        y.setInfix(s2);
        y.infixToPostfix();
        double fa = x.evaluatePostfix();
        double faplus = y.evaluatePostfix();
        double dir = ( faplus - fa ) / 0.0000000001 ;
        return dir ;
    }


}
