package snakenagini;

import javax.swing.JFrame;

public class SnakeNagini extends JFrame{
    NaginiS n1 = new NaginiS();
    public SnakeNagini() {
        setTitle("SNake NaGiNi");        
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(n1);
    }
    public static void main(String[] args) {
       SnakeNagini s1 = new SnakeNagini();
    }
    
}
