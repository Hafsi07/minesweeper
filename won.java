import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class won { 
    won(int s){
                

        JLabel l=new JLabel("Congratulations you won!");
        JButton btn=new JButton("Play again");
        JFrame f=new JFrame(); 
        
        l.setBounds(60,40,200,50);
        l.setSize(200,50);
        l.setFont(new Font("bold",Font.BOLD,15));

        
        
        btn.setBounds(95,100,140,40);
        btn.setSize(100,40);
        btn.setLayout(null);
        btn.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent ee){
                if (ee.getButton()== MouseEvent.BUTTON1){
                    f.dispose();
                    clean c=new clean(s);
                }
            }});
        
        
        f.add(l);
        f.add(btn);
        
        ImageIcon img= new ImageIcon("logo.png");
        f.setSize(300,200);
        f.setTitle("MineSweeper");
        f.setResizable(true);
        f.setBackground(new Color(0,0,255));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setIconImage(img.getImage());
        f.setLayout(null);
        f.setFocusTraversalPolicy(null);
        f.setVisible(true);

         


    }
}