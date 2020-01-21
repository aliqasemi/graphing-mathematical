
package data.structer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class DataStructer {

    public static void main(String[] args) {
        
 
        
        
        JFrame frame = new JFrame();
        DATA data = new DATA();
        
        frame.add(data);
        frame.setVisible(true);
        frame.setSize(10000 , 550);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
}
