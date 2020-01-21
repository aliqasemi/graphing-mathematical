
package data.structer;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Grapher extends JPanel {
    
    double a ;
    double b ;
    String s ;
    public Grapher()  
    {
        this.setSize(400,400);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.BLACK);
  
    }
    public void draw (double x_min, double x_max, String function)
    {
        this.a = x_min;
        this.b = x_max;
        this.s = function;
        
        repaint();
    }
    
    public void paintComponent(Graphics g)
    {
        
        g.clearRect(0, 0, 400, 400);
        g.drawLine(400, 200, -200, 200);
        g.drawLine(200, 400, 200, -200);
        Pos x = new Pos();
        for(int i = (int )(this.a) ; i < (int) this.b ; i ++){
            x.setInfix(this.s.replaceAll("x", "" + i));
           x.infixToPostfix();
           int r0 = (int)(x.evaluatePostfixDraw());
           x.setInfix(this.s.replaceAll("x", "" + (i + 1)));
           x.infixToPostfix();
          int r =(int) (x.evaluatePostfixDraw());
           g.drawLine((int) (i  + 200) ,200 - r0,(int)(i + 201), 200 - r);
            
        }
    }
    
}
