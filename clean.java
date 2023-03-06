import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import java.lang.Thread;

import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.BreakIterator;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;


public class clean extends JFrame {
    int count=0;
    public static int SIZE;
    public static int NBOMBS;

    public void updater(int s){
        this.SIZE=s;
        this.NBOMBS=SIZE*SIZE/8;    
    }

    public void lost(){ 
        System.out.println("you lost"+box.X[10]);

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if (buttons[i][j].value==9){
                    ImageIcon img= new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(40, 40,Image.SCALE_SMOOTH));
                    buttons[i][j].setIcon(img);
                    
                }
            }
        }
        loste fff=new loste(this,this.SIZE);

    }
    
    
    public void discover(int i,int j){ 
        if (buttons[i][j].flagged){     //check if it's flagged 
            return;
        }
        if (buttons[i][j].value==9){    //check if it's a bomb (you lose)
        lost();
    }
        else if (count==0){     //count is set to 0 when you haven't started the game yet
        count++;            //increments to indicate it's no the first time
        box.set_bombs(i,j);     //creation of bombs (squares of value=9)
        buttons[i][j].setEnabled(false);    //disabling the current button 
        discover(i,j);      //check the buttons surrounding it and each button being checked checks his surroundings 
        }else{
            if (buttons[i][j].value!=0){    //case you click on a square that has a value different than 0 or 9
                buttons[i][j].setText(Integer.toString(buttons[i][j].value));
                buttons[i][j].setForeground(Color.BLACK);
                buttons[i][j].setEnabled(false);
            }
            else {
                buttons[i][j].setEnabled(false);        //disabling the button
                System.out.print(Integer.toString(i)+"  "+Integer.toString(j)+"||");        
                
                //the four ifs are for the case where you click on a square that's on the edge cuz it may create some problem if we treat it like the rest 
                try {
                    if (i==0){ 
                        if(j+1<SIZE && buttons[i+1][j+1].isEnabled() ){discover(i+1,j+1);}
                        if(j+1<SIZE && buttons[i][j+1].isEnabled() ){discover(i,j+1);}
                        if(j>0 && buttons[i][j-1].isEnabled()){discover(i,j-1);}
                        if(j>0 && buttons[i+1][j-1].isEnabled()){discover(i+1, j-1);}
                        if(j>=0 && buttons[i+1][j].isEnabled()){discover(i+1,j);}
                    }
                    if (i==SIZE-1){ 
                        if(j+1<SIZE && buttons[i][j+1].isEnabled()){discover(i, j+1);}
                        if(j+1<SIZE && buttons[i-1][j+1].isEnabled()){discover(i-1, j+1);}
                        if(j<SIZE && buttons[i-1][j].isEnabled()){discover(i-1, j);}
                        if(j>0 && buttons[i-1][j-1].isEnabled()){discover(i-1, j-1);}
                        if(j>0 && buttons[i][j-1].isEnabled()){discover(i, j-1);}
                    }
                    
                    if (j==0 && i!=0){ 
                        if(i+1<SIZE && buttons[i+1][j+1].isEnabled() ){discover(i+1,j+1);}
                        if(i+1<SIZE && buttons[i+1][j].isEnabled() ){discover(i+1,j);}
                        if(i>0 && buttons[i-1][j].isEnabled() ){discover(i-1,j);}
                        if(i>0 && buttons[i-1][j+1].isEnabled() ){discover(i-1, j+1);}
                        if(i>0 && buttons[i][j+1].isEnabled() ){discover(i, j+1);}
                    }
                    if (j==SIZE-1 && i!=SIZE-1){ 
                        discover(i+1, j);
                        discover(i+1, j-1);
                        discover(i, j-1);
                        if(i>0 && buttons[i-1][j-1].isEnabled()){discover(i-1, j-1);}
                        if(i>0 && buttons[i-1][j].isEnabled()){discover(i-1, j);}
                    }
                    if(i<SIZE-1 && i>0 && j<SIZE-1 && j>0){
                        if(buttons[i+1][j+1].isEnabled()){discover(i+1,j+1);}
                        if( buttons[i][j+1].isEnabled()){discover(i,j+1);}
                        if(buttons[i-1][j+1].isEnabled()){discover(i-1,j+1);}
                        if(buttons[i-1][j].isEnabled()){discover(i-1,j);}
                        if(buttons[i-1][j-1].isEnabled()){discover(i-1,j-1);}
                        if(buttons[i][j-1].isEnabled() ){discover(i,j-1);}
                        if(buttons[i+1][j-1].isEnabled()){discover(i+1,j-1);}
                        if(buttons[i+1][j].isEnabled()){discover(i+1,j);}
                    }
                }
                catch (Exception e){System.out.println(e+"//"+Integer.toString(i)+" "+Integer.toString(j));}
                System.out.println("passed");
                
            }
        }
        // after each click /clear of squares, it is checked to see if you have unlocked every unlockable square 
        int zeros=0;
        for (int h=0;h<SIZE;h++){
            for (int a=0;a<SIZE;a++){
                if (buttons[h][a].isEnabled()){zeros++;}
            }
        }
        if (zeros==NBOMBS){
            this.dispose();
            new won(this.SIZE);
        }
    }
    
    public static box[][] buttons;
    
    clean(int s){
        updater(s);
        buttons=new box[SIZE][SIZE];
        
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                buttons[i][j]=new box(i*30,j*30,30,30); 
                this.add(buttons[i][j]);
            }
        }
        
        ImageIcon img= new ImageIcon("logo.png");
        this.setSize(752,752);
        this.setTitle("MineSweeper");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(img.getImage());
        this.setVisible(true);
        this.setLayout(new GridLayout(SIZE,SIZE,0,0));
        this.setFocusTraversalPolicy(null);


        
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                buttons[i][j].assign_value(i,j);
            }
        }

        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                final int x=i,y=j;
                buttons[i][j].addActionListener(e->discover(x,y));
                buttons[i][j].addMouseListener(new MouseInputAdapter() {
            
                    public void mouseClicked(MouseEvent ee){
                    if (ee.getButton()== MouseEvent.BUTTON3 && buttons[x][y].isEnabled() ){
                        if (buttons[x][y].flagged==false){
                            buttons[x][y].setText("🚩");
                            buttons[x][y].setOpaque(true);
                            buttons[x][y].setForeground(Color.red);
                            buttons[x][y].flagged=true;
                        }else {
                            buttons[x][y].setText("");
                            buttons[x][y].flagged=false;
                        }
                    }
                    }
                });
            }
        }
        

    }


    public class box extends JButton{
        int value=0;
        boolean flagged=false;
        ImageIcon imgg= new ImageIcon("logo.png");

        public box (int x,int y,int w,int h){
            new JButton();
            this.setBounds(x,y,w,h);
        }

        public static int[] X=new int[NBOMBS+9],Y=new int[NBOMBS+9]; // X and Y represent arrays holding the x and y value of every bomb to be and dodges the 9 squares middled by the user's click.
        
        public static void set_bombs(int n,int m){
            Random random=new Random();
            for (int i=0;i<NBOMBS+1;i++){
                X[i]=0;
                Y[i]=0;
            }
            X[0]=n;Y[0]=m;
            X[1]=n+1;Y[1]=m;
            X[2]=n;Y[2]=m+1;
            X[3]=n+1;Y[3]=m+1;
            X[4]=n-1;Y[4]=m;
            X[5]=n;Y[5]=m-1;
            X[6]=n-1;Y[6]=m-1;
            X[7]=n+1;Y[7]=m-1;
            X[8]=n-1;Y[8]=m+1;
            buttons[n][m].value=0;
            for (int i=9;i<NBOMBS+9;i++){
                int x=random.nextInt(SIZE),y=random.nextInt(SIZE);
                
                for (int f=0;f<9;f++){
                    if (X[f]==x && Y[f]==y){
                        x=random.nextInt(SIZE);
                        y=random.nextInt(SIZE);
                    }
                }
                
                X[i]=x;
                Y[i]=y;
                
                buttons[x][y].value=9;


        
            }

            for (int i=0;i<SIZE;i++){
                for(int j=0;j<SIZE;j++){
                    buttons[i][j].assign_value(i,j); //assign values to every square on the board relative to the number of bombs 
                }
            }
    
        }


        public void assign_value (int i,int j){
            int k=0;
            if (buttons[i][j].value!=9){
                if (i==0){
                    if (buttons[i+1][j].value==9){k++;};
                    if (j!=0){
                        if (buttons[i][j-1].value==9){k++;};
                        if (buttons[i+1][j-1].value==9){k++;};
                    }
                    if(j!=SIZE-1){ 
                        if (buttons[i+1][j+1].value==9){k++;};
                        if (buttons[i][j+1].value==9){k++;};
                    }
                }
                if (i==SIZE-1){
                    if (buttons[i-1][j].value==9){k++;};

                    if (j!=0){
                        if (buttons[i-1][j-1].value==9){k++;};
                        if (buttons[i][j-1].value==9){k++;};
                    }
                    if (j!=SIZE-1){ 
                        if (buttons[i][j+1].value==9){k++;};
                        if (buttons[i-1][j+1].value==9){k++;};
                    }
                }
                if (j==0 && i!=0 && i!=SIZE-1){
                    if (buttons[i][j+1].value==9){k++;};
                    if (i!=0 ){
                        if (buttons[i-1][j].value==9){k++;};
                        if (buttons[i-1][j+1].value==9){k++;};
                    }
                    if(i!=SIZE-1 && i!=0){ 
                        if (buttons[i+1][j+1].value==9){k++;};
                        if (buttons[i+1][j].value==9){k++;};
                    }
                }
                if (j==SIZE-1 && i!=SIZE-1){
                    if (buttons[i][j-1].value==9){k++;};

                    if (i!=0 && i!=SIZE-1){
                        if (buttons[i-1][j-1].value==9){k++;};
                        if (buttons[i-1][j].value==9){k++;};
                    }
                    if (i!=SIZE-1 && i!=0){ 
                        if (buttons[i+1][j].value==9){k++;};
                        if (buttons[i+1][j-1].value==9){k++;};
                    }
                }
            }
            if (i <SIZE-1 && i>0 && j<SIZE-1 && j>0){
                if (buttons[i+1][j].value==9){k++;};
                if (buttons[i][j+1].value==9){k++;};
                if (buttons[i+1][j+1].value==9){k++;};
                if (buttons[i-1][j-1].value==9){k++;};
                if (buttons[i][j-1].value==9){k++;};
                if (buttons[i-1][j].value==9){k++;};
                if (buttons[i-1][j+1].value==9){k++;};
                if (buttons[i+1][j-1].value==9){k++;};
            }
            if(buttons[i][j].value!=9){
                buttons[i][j].value=k;
            }
        }
    }

}
