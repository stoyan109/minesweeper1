package mindsweeper;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {

	public boolean resetter = false;
	
	public boolean  flagger= false; 
	
	Date startDate = new Date();
	
	Date endDate;
	
	int spacing = 5;
	
	int neighs = 0;
	
     String vicMes = "nothing";
	
	public int mx = -100;
    public int my = -100;
    
    public int smileyX = 605;
    public int smileyY = 5;
    
    public int smileycenterX = smileyX+35;
    public int smileycenterY= smileyY+35;

    public int flaggerX = 445;
    public int flaggerY = 5;
    
    public int flaggerCenterX = flaggerX +35;
    public int flaggerCenterY = flaggerY +35;

    
    
    public int timeX = 1120;
    public int timeY = 5;
    
   public int  vicMesX= 50;
   public int  vicMesY= -50;
	
    public int sec = 0;
     
    public boolean hap = true;  
    
    public boolean victory = false;
    
    public boolean defeat = false;
    
    Random rand = new Random();
	
    int [][] mines = new int [16][9];
    int [][] neighbours = new int [16][9];
    boolean[][] revealed = new boolean [16][9];
    boolean[][] flagged = new boolean [16][9];

	private int flaggercenterY;
  
	public GUI() {
	this.setTitle("minesweeper");
	this.setSize(1286, 829);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setResizable(false);
	
	
			for (int i = 0; i<16;i++) {
		for (int j = 0 ;j<9;j++) {
		if(rand.nextInt(100)< 24)
			mines[i][j]=1;
       
		else {
			mines[i][j] = 0 ;
		}
		revealed [i][j] = false;
     }
	}
		for (int i = 0; i<16;i++) {
			for (int j = 0 ;j<9;j++) {
				neighs =0;
				for (int m = 0; m<16;m++) {
					for (int n = 0 ;n<9;n++) {
					if (!(m == i && n == j)) 
						if (isN(i,j,m,n)== true) {
							neighs++;
						}
	          }
					neighbours[i][j]=neighs;
	    }	
			
    }
}
	
	Board board = new Board();
	this.setContentPane(board);
	
	Move move = new Move();
	this.addMouseMotionListener(move);
	
	Click click =new Click();
	this.addMouseListener(click);
	
	}
	public class Board extends JPanel {
		public void paintComponent(Graphics g ) {
			g.setColor(Color .DARK_GRAY);
			g.fillRect(0, 0, 1280, 820);
			g.setColor(Color.GRAY);
			for (int i = 0; i<16;i++) {
				for (int j = 0 ;j<9;j++) {
					g.setColor(Color.gray);
					
					
					
					if(revealed[i][j] == true) {
						g.setColor(Color.white);
						if (mines[i][j]==1) {
							g.setColor(Color.red);
						}
					}
					if (mx >= spacing+i *80 && mx < i *80+80 - spacing && my >=spacing+j*80+106 && my <j*80+186-spacing) {
						g.setColor(Color.lightGray);
					}
						
					g.fillRect(spacing+i*80, spacing+j*80+80, 80 - 2*spacing, 80 - 2*spacing);
					if(revealed[i][j] == true) {
						g.setColor(Color.black);
						if (mines[i][j] == 0 && neighbours [i][j] != 0) 	{
							if ( neighbours [i][j] ==1) {
								g.setColor(Color.blue);
							}else if (neighbours [i][j] ==2){
								g.setColor(Color.green);
							}else if (neighbours [i][j] ==3){
								
								g.setColor(Color.red);
							}	
							else if (neighbours [i][j] ==4)
							{
							
								g.setColor(new Color(0,0,128));
								
							}
							else if (neighbours [i][j] ==5){
								g.setColor(new Color(178,34,34));
								
							}else if (neighbours [i][j] ==6){
								
								g.setColor(new Color(72,209,204));
								
							}else if (neighbours [i][j] ==8){
								g.setColor( Color.darkGray);
								}	
								
						g.setFont(new Font("Tahoma",Font.BOLD,40));
						g.drawString(Integer.toString(neighbours[i][j]), i*80+27, j*80+80+55);;
					}else if (mines[i][j]==1){
						g.fillRect(i*80+10+20, +j*80+80+20 , 20, 40);
						g.fillRect(i*80+20, +j*80+80+10+20, 40, 20);
						g.fillRect(i*80+5 +20, +j*80+80+5+20, 30, 30);
						g.fillRect(i*80+38, j*80+80+15, 4, 50);
						g.fillRect(i*80+15, j*80+80+38, 50, 4);
						g.fillRect(i*80+20, j*80+80+20,5,5);
						g.fillRect(i*80+55, j*80+80+20,5,5);
						g.fillRect(i*80+20, j*80+80+55,5,5);
						g.fillRect(i*80+55, j*80+80+55,5,5);
						g.setColor(Color.white);
						g.fillRect(i*80+29, j*80+80+29,9,9);
						
					}
					
						
				}
					if (flagged[i][j]==true) {
						g.setColor(Color.BLACK);
						g.fillRect(i*80+31,j*80+80+15, 7, 40);
						g.fillRect(i*80+20, j*80+80+50, 30, 10);
						g.setColor(Color.red);
						g.fillRect(i*80+13, j*80+80+15, 18, 15);
					}
					
			}
				
		}
		
			g.setColor(Color.yellow);
			g.fillOval(smileyX, smileyY, 70, 70);
			g.setColor(Color.black);
			g.fillOval(smileyX+15, smileyY+20, 10, 10);
			g.fillOval(smileyX+45, smileyY+20, 10, 10);
			if(hap == true ) {
			g.fillRect(smileyX+20, smileyY+50,30, 5);
			g.fillRect(smileyX+17, smileyY+45,5, 5);
			g.fillRect(smileyX+48, smileyY+45,5, 5);
			}else {
				g.fillRect(smileyX+20, smileyY+50,30, 5);
				g.fillRect(smileyX+17, smileyY+55,5, 5);
				g.fillRect(smileyX+48, smileyY+55,5, 5);
			}
			
			g.setColor(Color.BLACK);
			g.fillRect(flaggerX+31,flaggerY+15, 7, 40);
			g.fillRect(flaggerX+20, flaggerY+50, 30, 10);
			g.setColor(Color.red);
			g.fillRect(flaggerX+13, flaggerY+15, 18, 15);
			
			if(flagger == true) {
				g.setColor(Color.red);
			
			g.setColor(Color.BLACK);
			g.drawOval(flaggerX,flaggerY, 70, 70);
			}
			
			g.setColor(Color.black);
			g.fillRect(timeX, timeY, 140, 70);
			if(defeat == false && victory == false) {
			sec = (int) ((new Date().getTime()-startDate.getTime())/ 1000);
			}
			if (sec > 999) {
				sec = 999;
			}
			g.setColor(Color.white);
			if(victory== true) {
				g.setColor(Color.green);
			}	else if(defeat== true) {
				g.setColor(Color.red);
			}
			g.setFont(new Font("Tahoma",Font.PLAIN,80));
			if(sec < 10) {
				g.drawString("00"+Integer.toString(sec), timeX, timeY+65);
			}else if (sec<100) {
				g.drawString("0"+Integer.toString(sec), timeX, timeY+65);
			}else {
				g.drawString(Integer.toString(sec), timeX, timeY+65);	
	 }
		if (victory == true ) {
			g.setColor(Color.green);
			vicMes = "YOU WIN";
		}else if(defeat== true) {
			g.setColor(Color.red);
			vicMes = "YOU LOSE";
		}
		
		if(victory == true||defeat == true) {
			 vicMesY = - 50 + (int) (new Date().getTime() - endDate.getTime()) / 10;
			if(vicMesY > 70) {
				vicMesY = 70;
			}
			g.setFont( new Font("Tahoma", Font.PLAIN,70));
			 g.drawString(vicMes,vicMesX , vicMesY);
			
		}
		
       }
	} 
	
	
	public class Move implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
			
			//System.out.println("X : " + mx + ",Y:" +my); 
			
		}
		
	}
	
	public class Click implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			mx = e.getX();
			my = e.getY();
			
			
			
			
			if(inBoxX()!= -1 && inBoxX() != -1) {
				System.out.println("the mouse is in the ["+ inBoxX() +","+ inBoxY()+"] , number of mine neighs" +neighbours  [ inBoxX()][ inBoxY()]);
			if (flagger == true&&revealed[inBoxX()][inBoxY()]==false ) {
				if (flagged[inBoxX()][inBoxY()] = false) {
				flagged[inBoxX()][inBoxY()] = true;
			 
			}else {
				flagged[inBoxX()][inBoxY()] = true;	
			 }
			}else {
				revealed[inBoxX()][inBoxY()]= true ;
			}
		
			}else {
				
				System.out.println("the pointer is not in a box");
			
			if(inSmiley()== true) {
			  resetAll();
			}
	
			
			if (inFlagger() == true) {
				if(flagger = true) {
					flagger = true;
				}else {
					flagger = false;
				}
				
			}	
			
			}
			
		}
			
			
			
			
			
		

		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	
	public void checkvict() {
		if (defeat == false) {
		for (int i = 0; i<16;i++) {
			for (int j = 0 ;j<9;j++) {
				if(revealed[i][j]==true && mines[i][j]==1) {
					defeat = true;
					hap = false;
					endDate= new Date();
				}
			}
		}
		}
		if (totalBox()>=144-totalMines() && victory == false) {
			victory = true;
			endDate = new Date();
		}
	}
	public int totalMines() {
		 int total = 0;
		for (int i = 0; i<16;i++) {
			for (int j = 0 ;j<9;j++) {
				if(mines[i][j]==1) {
					total++;
				}
			}
		}
		return total;
	}
	public int totalBox() {
		int total = 0;
		for (int i = 0; i<16;i++) {
			for (int j = 0 ;j<9;j++) {
				if(revealed[i][j]==true) {
					total++;
				}
			}
		}
		return 0;
	}
		
	
	
	public void resetAll(){
		resetter = true;
		
		startDate = new Date();
		
		vicMesY = -50;
		
		hap = true;
		victory = false;
		defeat = false;
		
		for (int i = 0; i<16;i++) {
	for (int j = 0 ;j<9;j++) {
	if(rand.nextInt(100)< 24)
		mines[i][j]=1;
   
	else {
		mines[i][j] = 0 ;
	}
	revealed [i][j] = false;
	flagged [i][j] = false;
 }
}
	for (int i = 0; i<16;i++) {
		for (int j = 0 ;j<9;j++) {
			neighs =0;
			for (int m = 0; m<16;m++) {
				for (int n = 0 ;n<9;n++) {
				if (!(m == i && n == j)) 
					if (isN(i,j,m,n)== true) {
						neighs++;
					}
          }
				neighbours[i][j]=neighs;
			}
		}
		
	}
	resetter = false;
		
}
	public boolean inSmiley() {
        int dif =(int) Math.sqrt(Math.abs(mx-smileycenterX)*Math.abs(mx-smileycenterX)+(my-smileycenterY)*Math.abs(my-smileycenterY));
		if(dif < 35) {
			return true;
		}
		return false;
	}
	public boolean inFlagger() {
        int dif =(int) Math.sqrt(Math.abs(mx-flaggerCenterX)*Math.abs(mx-flaggerCenterX)+(my-flaggerCenterY)*Math.abs(my-flaggerCenterY));
		if(dif < 35) {
			return true;
		}
		return false;
	}
		public int inBoxX() {
			for (int i = 0; i<16;i++) {
				for (int j = 0 ;j<9;j++) {
					if (mx >= spacing+i *80 && mx < i *80+80 - spacing && my >=spacing+j*80+106 && my <j*80+186-spacing) 
				 return (i);
				}
		   }
	
		return (-1);
	}	
		public int inBoxY() {
			for (int i = 0; i<16;i++) {
				for (int j = 0 ;j<9;j++) {
					if (mx >= spacing+i *80 && mx < i *80+80 - spacing && my >=spacing+j*80+106 && my <j*80+186-spacing) 
				 return (j);
			
		}
		
	
}
			return (-1);
		}
		
		
		public boolean isN (int mX,int mY,int cX,int cY) {
			if(mX-cX<2 && mX - cX > -2 && mY-cY <2 && mY - cY > -2&& mines [cX][cY] ==1 ) {
				return true;
			}
			return false ;
			
			
		}
}

