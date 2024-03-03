package Project_pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;




public class Adversary {
	
	public double x,y;
	public int width,height;
	public double speed;
	public double randomError;
	
	public Adversary(int x,int y,double speed) {
		
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 40;
		this.speed = speed;
		this.randomError = 0.03;
		
	}
	
	public void tick() {
		double deltaY = (Pong.ball.y-height/2 - y) * (speed + getRandomError());
        y += deltaY;
        
        if(y+height > Pong.HEIGHT) {
			y = Pong.HEIGHT - height;
			}
		else if(y-20<0) {
			y = 20;
			}
        }
	
	private double getRandomError() {
        
        Random random = new Random();
        return (random.nextDouble() * 2 - 1) * randomError;
    }
	
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width,height);
		
	}
	public void resetPosition() {
	    this.x = Pong.WIDTH - 5;
	    this.y = Pong.HEIGHT / 2 - height/2 + 10;
	}

}
