package Project_pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	public double x,y;
	public int width,height;
	
	public double dx,dy;
	public double speed = 1;
	
	public int player_Poit = 0, adversary_Poit = 0;
	
	
	public Ball(int x,int y) {
		
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angle = new Random().nextInt(225 - 135) + 135;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		
	}
	
	public void tick() {
	    if (y + dy * speed + height >= Pong.HEIGHT+3 || y + dy * speed <= 21) {
	        dy *= -1; 
	    }

	    x += dx * speed;
	    y += dy * speed;

	    
	    Rectangle boundsPlayer = new Rectangle(Pong.player.x, Pong.player.y, Pong.player.width, Pong.player.height);
	    Rectangle boundsAdversary = new Rectangle((int) Pong.adversary.x, (int) Pong.adversary.y, Pong.adversary.width, Pong.adversary.height);
	    if (boundsPlayer.intersects(x, y, width, height)) {
	    	
	    	int angle = new Random().nextInt(250 - 135) + 135+ 1;	
	    	dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dx < 0)
				dx*= -1.05; 
	    	
	    }	    	

	    
	    
	        
	        if (boundsAdversary.intersects(x, y, width, height)) {
	            int angle = new Random().nextInt(250 - 135) + 135 + 1;
	            dx = Math.cos(Math.toRadians(angle));
	            dy = Math.sin(Math.toRadians(angle));
	            if (dx > 0)
	                dx *= -1.05;
	        }

	    if (x + width >= Pong.WIDTH) {
	        player_Poit += 1;
	        resetLocation();
	    } else if (x <= 0) {
	        adversary_Poit += 1;
	        resetLocation();
	    }
	}

	private void resetLocation() {
		
		
	    x = Pong.WIDTH / 2;
	    y = Pong.HEIGHT / 2+10;
	    
	    int angle = new Random().nextInt(225 - 135) + 135;
	    dx = Math.cos(Math.toRadians(angle));
	    dy = Math.sin(Math.toRadians(angle));
	    
	    Pong.player.resetPosition();
	    Pong.adversary.resetPosition();
	}

	
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, width,height);
		
	}

}
