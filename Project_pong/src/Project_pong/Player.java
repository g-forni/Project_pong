package Project_pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean up,down;
	
	public int x,y;
	public int width,height;
	
	public Player(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 40;
	}
	
	public void tick() {
		if(up) {
			y--;
		}
		else if(down) {
			y++;
		}
		if(y+height > Pong.HEIGHT) {
			y = Pong.HEIGHT-height ;
		}
		else if(y-20<0) {
			y = 20;
		}
		
	}
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width,height);
	}
	public void resetPosition() {
	    this.x = 0;
	    this.y = Pong.HEIGHT / 2 - height/ 2+10; 
	}

}
