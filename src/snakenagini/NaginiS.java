package snakenagini;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
public class NaginiS extends JPanel implements ActionListener{
    int size = 950;
    int sSize = 3;
    int ySize = 20;
    int starX;
    int starY;
    int deskX1;
    int deskY1;
    int deskX2;
    int deskY2;
    int deskX3;
    int deskY3;
    int deskX4;
    int deskY4;
    int heartX;
    int heartY;
    int x[] = new int[size];
    int y[] = new int[size];
    boolean up = false;
    boolean down = false;
    boolean right = true;
    boolean left = false;
    boolean game = true;
    Image star;
    Image snake;
    Image desk1;
    Image desk2;
    Image heart;
    int achko = 0;
    int bonus = 0;
    
    Random r1 = new Random();
    Font f1 = new Font("Calibri", 4, 48);
    JLabel l1 = new JLabel("0");
    
  
        public NaginiS(){
        setSize(900, 900);         
        gameS();
        loadImages();
        setBackground(Color.black);
        addKeyListener(new rule());
        setFocusable(true);
        add(l1);
       
    }
    public void starCreat() {
        starX = r1.nextInt(30) * ySize;
        starY = r1.nextInt(30) * ySize;
    }

    public void loadImages() {
        ImageIcon i1 = new ImageIcon("star.png");
        star = i1.getImage();
        ImageIcon i2 = new ImageIcon("snake.png");
        snake = i2.getImage();
        ImageIcon i3 = new ImageIcon("desk1.png");
        desk1 = i3.getImage();
        ImageIcon i4 = new ImageIcon("desk2.png");
        desk2 = i4.getImage();
        ImageIcon i5 = new ImageIcon("heart.png");
        heart = i5.getImage();
    }

    public void gameS() {
        for (int i = 0; i < sSize; i++) {
            x[i] = 300 + i * ySize;
            y[i] = 300;
        }
        Timer t1 = new Timer(100, this);
        t1.start();
        starCreat();
        deskCreat();        
    }
    public void notStar(){
        for (int i = 1; i < sSize + 1; i++) {
            if ((starX == x[i] && starY == y[i])
                    || (starX == deskX1 && starY == deskY1)
                    || (starX == deskX2 && starY == deskY2)
                    || (starX == deskX3 && (starY == deskY3 
                    || starY == deskY3 + 10
                    || starY == deskY3 + 20 || starY == deskY3 + 30))
                    || ((starX == deskX4 || starX == deskX4 + 10
                    || starX == deskX4 + 20 
                    || starX == deskX4 + 30) && starY == deskY4)) {
                starCreat();
                System.out.println("juldiz");
            }
        }
    }
    public void notHeart(){
        for (int i = 1; i < sSize + 1; i++) {    
            if ((heartX == x[i] && heartY == y[i])
                    || (heartX == deskX1 && heartY == deskY1)
                    || (heartX == deskX2 && heartY == deskY2)
                    || (heartX == deskX3 && (heartY == deskY3 
                    || heartY == deskY3 + 10
                    || heartY == deskY3 + 20 || heartY == deskY3 + 30))
                    || ((heartX == deskX4 || heartX == deskX4 + 10
                    || heartX == deskX4 + 20 
                    || heartX == deskX4 + 30) && heartY == deskY4)) {
                heartCreat();
                System.out.println("jurek");
            }
        }
    }

    public void eatStar() {
        if (x[0] == starX && y[0] == starY) {
            sSize++;
            achko += 5;
            bonus++;
            l1.setForeground(Color.white);
            l1.setText("Score:" + achko);
            starCreat();
        }
        if (x[0] == heartX && y[0] == heartY) {
            achko += 100;
            l1.setText("Score:" + achko);
            bonus = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        go();
        eatStar();
        border();
        notStar();
        
    }
    public void border() {
        for (int i = 1; i < sSize + 1; i++) {
            if ((sSize > 4 && x[0] == x[i] && y[0] == y[i])
                    || (x[0] == deskX1 && y[0] == deskY1)
                    || (x[0] == deskX2 && y[0] == deskY2)
                    || (x[0] == deskX3 && (y[0] == deskY3 
                    || y[0] == deskY3 + 10
                    || y[0] == deskY3 + 20 || y[0] == deskY3 + 30))
                    || ((x[0] == deskX4 || x[0] == deskX4 + 10
                    || x[0] == deskX4 + 20 
                    || x[0] == deskX4 + 30) && y[0] == deskY4)) {
                game = false;
            }
        }
        if (y[0] < 0) {
            
            y[0] = size - 50;
        }
        if (y[0] > size) {
            y[0] = 0;
        }
        if (x[0] < 0) {
            x[0] = size - 100;
        }
        if (x[0] > size) {
            x[0] = 0;
        }

    }
    public void go() {
        for (int i = sSize; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (up) {
            y[0] -= ySize;
        }
        if (down) {
            y[0] += ySize;
        }
        if (right) {
            x[0] += ySize;
        }
        if (left) {
            x[0] -= ySize;
        }
        repaint();
    }

    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(desk1, deskX1, deskY1, this);
        g.drawImage(desk2, deskX2, deskY2, this);
        g.drawImage(desk1, deskX3, deskY3, 30, 60, this);
        g.drawImage(desk2, deskX4, deskY4, 60, 30, this);        
        if (game) {
            g.drawImage(star, starX, starY, this);
            for (int i = 0; i < sSize; i++) {
                g.drawImage(snake, x[i], y[i], this);
            if(bonus == 5){
                notHeart();
                g.drawImage(heart, heartX, heartY, this);
                }}            
        } else {
            g.setColor(Color.blue);
            g.setFont(f1);
            g.drawString("Game Over", size / 5, size / 10);
        }
        
    }

    class rule extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {            
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                down = false;
                up = false;
            }
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                down = false;
                up = false;
            }

        }

    }
    public void deskCreat(){               
        deskX1 = r1.nextInt(30) * ySize;
        deskY1 = r1.nextInt(30) * ySize;
        deskX2 = r1.nextInt(20) * ySize;
        deskY2 = r1.nextInt(20) * ySize;
        deskX3 = r1.nextInt(30) * ySize;
        deskY3 = r1.nextInt(30) * ySize;
        deskX4 = r1.nextInt(20) * ySize;
        deskY4 = r1.nextInt(20) * ySize;        
    }
   public void heartCreat() {
        heartX = r1.nextInt(30) * ySize;
        heartY = r1.nextInt(30) * ySize;
    }
   

}


   