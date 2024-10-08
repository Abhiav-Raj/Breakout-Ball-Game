package breackoutball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private int score = 0;
	
    private int totalBricks = 28;
    
    private Timer timer;
    private int  delay = 8;
    
    private int playerx = 350;
    private int ballposx = 120;
    private int ballposy = 350;
    private int ballxdir = -1;
    private int ballydir = -2;
    
    private MapGenerator map;
    
    public Gameplay() {
    	map=new MapGenerator(4,7);
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    	timer = new Timer(delay, this);
    	timer.start();
    	
    	}
    	
    	public void paint(Graphics g) {
    		//background
    		g.setColor(Color.black);
    		g.fillRect(1, 1, 692, 592);
    		
    		//drawing map
    		map.draw((Graphics2D)g);
    		
    		
    		//border
    		g.setColor(Color.yellow);
    		g.fillRect(1,1,692,3);
    		g.fillRect(0,3,3,592);
    		g.fillRect(691,3,3,592);
    		
    		//score
    		g.setColor(Color.white);
    		g.setFont(new Font("sreif",Font.BOLD,25));
    		g.drawString(""+score, 599,30 );
    		
    		
    		
    		
    		//paddle
    		g.setColor(Color.green);
    		g.fillRect(playerx, 550, 100, 8);
    		
    		// the ball
    		g.setColor(Color.red);
    		g.fillOval(ballposx, ballposy, 20, 20);	
    		
    		if(totalBricks<=0) {
    			play = false;
    			ballxdir=0;
    			ballydir=0;
                g.setColor(Color.red);
                g.setFont(new Font("sreif",Font.BOLD,30));
                g.drawString("You Won ", 260,300);
    			
                g.setFont(new Font("sreif",Font.BOLD,20));
                g.drawString("Press Enter to Restart ", 230,350);
    			
    		}
    		
    		
    		
    		
    		
    		if(ballposy > 570) {
    			play = false;
    			ballxdir=0;
    			ballydir=0;
                g.setColor(Color.red);
                g.setFont(new Font("sreif",Font.BOLD,30));
                g.drawString("Game Over,Scores:"+score,190,300);
    			
                g.setFont(new Font("sreif",Font.BOLD,20));
                g.drawString("Press Enter to Restart ", 230,350);
    			
    		}
    		g.dispose();
    	}
    	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8))) {
				ballydir =-ballydir;
			}
			A :for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int brickx = j*map.brickwidth+80;
						int bricky = i*map.brickheight+50;
						int brickwidth=map.brickwidth;
						int brickheight=map.brickheight;
						
						Rectangle rect= new Rectangle(brickx,bricky,brickwidth,brickheight);
						Rectangle ballRect=new Rectangle(ballposx,ballposy,20,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickvalue(0,i,j);
							totalBricks--;
							score+=5;
							
							if(ballposx + 19 <= brickRect.x|| ballposx + 1>= brickRect.x + brickRect.width) {
								ballxdir = -ballxdir;
							}else {
								ballydir = -ballydir;
							}
							break A;
						}
						
						}
				}
			}
			
		
			ballposx += ballxdir;
			ballposy += ballydir;
			
			if(ballposx < 0) {
				ballxdir = -ballxdir;
				}
				if(ballposy < 0) {
				ballydir = -ballydir;
				}
				if(ballposx > 670) {
			    ballxdir =-ballxdir;
			   }
		    }
		
		repaint();
		
		
	}

	
	public void keyTyped(KeyEvent e) {
		
	}
    public void keyReleased(KeyEvent e) {
		
	}

	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerx>=600) {
				playerx=600;
				}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerx<10) {
				playerx=10;
				}
			else {
				moveLeft();
			
		}
			
		}	
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				ballposx=120;
				ballposy=350;
				ballxdir=-1;
				ballydir=-2;
				playerx=310;
				score=0;
				totalBricks=28;
				map=new MapGenerator(4,7);
			    
				repaint();
				
			}
		}
	}
	public void moveRight(){
		play=true;
		playerx+=20;
		
	}
	public void moveLeft(){
		play=true;
		playerx-=20;
		
	}

	
	

}
