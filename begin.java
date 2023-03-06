import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class begin{ 
    begin(){
        
        
        JLabel l=new JLabel("Choose difficulty mode:");
        JButton btn=new JButton("EASY");
        JButton btn1=new JButton("There is no in between");
        JButton btn2=new JButton("hard");
        JFrame f=new JFrame(); 
        
        l.setBounds(60,40,200,50);
        l.setSize(200,50);
        l.setFont(new Font("bold",Font.BOLD,15));

        
        
        btn.setBounds(95,100,140,40);
        btn.setSize(70,30);
        btn.setLayout(null);
        btn.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent ee){
                if (ee.getButton()== MouseEvent.BUTTON1){
                    f.dispose();
                    clean c=new clean(9);
                }
            }});
        
        btn1.setBounds(45,150,140,40);
        btn1.setSize(170,30);
        btn1.setLayout(null);
        btn1.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent ee){
                if (ee.getButton()== MouseEvent.BUTTON1){
                    btn1.setText("hahahahahahahahaha");
                    btn1.setEnabled(false);
                }
            }});
        
        btn2.setBounds(95,200,140,40);
        btn2.setSize(70,30);
        btn2.setLayout(null);
        btn2.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent ee){
                if (ee.getButton()== MouseEvent.BUTTON1){
                    f.dispose();
                    clean c=new clean(16);
                }
            }});
        
        
        f.add(l);
        f.add(btn);
        f.add(btn1);
        f.add(btn2);
        
        ImageIcon img= new ImageIcon("logo.png");
        f.setSize(300,300);
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