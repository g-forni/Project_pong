package Project_pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Pong extends Canvas implements Runnable,KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 160;
	public static int HEIGHT = 160;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Adversary adversary;
	public static Ball ball;
	
	public Pong() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(0, HEIGHT/2 - 10);
		adversary = new Adversary(WIDTH-5, HEIGHT/2 - 10 ,0.04);
		ball = new Ball(WIDTH/2,HEIGHT/2+10);
		this.layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	public static void main(String[] args) {
		
		Pong pong = new Pong();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pong);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(pong).start();
		
		
	}
	
	public void tick() {
		player.tick();
		adversary.tick();
		ball.tick();
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
			
		Graphics g = this.layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, 20);

		g.setFont(g.getFont().deriveFont(9f));
		
		g.setColor(Color.white);
		String texto1 = "W = up";
		String texto2 = "S = down";
		String texto3 =  String.valueOf(ball.player_Poit);
	    String texto4 =  String.valueOf(ball.adversary_Poit);
	    
	    int larguraTexto1 = g.getFontMetrics().stringWidth(texto1);
	    int larguraTexto2 = g.getFontMetrics().stringWidth(texto2);
	    int larguraTexto3 = g.getFontMetrics().stringWidth(texto3);
	    int larguraTexto4 = g.getFontMetrics().stringWidth(texto4);
	    
	    g.drawString(texto1, (WIDTH - larguraTexto1) / 2, 9);
	    g.drawString(texto2, (WIDTH - larguraTexto2) / 2, 19);
	    g.drawString(texto3, 15-larguraTexto3, 14);
	    g.drawString(texto4, 150-larguraTexto4, 14);
		
		
		
		g.setColor(Color.white);
		g.fillRect(0, 19, WIDTH, 1);
	    
		player.render(g);
		adversary.render(g);
		ball.render(g);
		
		g.dispose();
		g = bs.getDrawGraphics();
		
		g.drawImage(layer, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
