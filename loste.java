import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class loste{ 
    loste(clean hh,int s){
        JLabel l=new JLabel("You lost! so sad :(");
        JButton btn=new JButton("Retry");
        JFrame fff=new JFrame(); 
        
        l.setBounds(70,40,200,50);
        l.setSize(200,50);
        l.setFont(new Font("bold",Font.BOLD,15));

        
        
        btn.setBounds(105,100,70,20);
        btn.setSize(70,30);
        btn.setLayout(null);
        btn.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent ee){
                if (ee.getButton()== MouseEvent.BUTTON1){
                    hh.dispose();
                    fff.dispose();
                    new clean(s);
                }
            }});
        
        
        fff.add(l);
        fff.add(btn);
        
        ImageIcon img= new ImageIcon("logo.png");
        fff.setSize(300,200);
        fff.setTitle("MineSweeper");
        fff.setResizable(true);
        fff.setBackground(new Color(0,0,255));
        fff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fff.setIconImage(img.getImage());
        fff.setLayout(null);
        fff.setFocusTraversalPolicy(null);
        fff.setVisible(true);

         


    }
}