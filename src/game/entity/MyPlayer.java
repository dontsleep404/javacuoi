package game.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

import game.Game;
import network.custom.CSocket;
import network.custom.cpacket.CPacketDamage;
import network.custom.cpacket.CPacketPlayerPosition;
import network.custom.cpacket.CPacketShoot;

public class MyPlayer extends Player{
	public ArrayList<Particle> bullets = new ArrayList<Particle>();
	long lastShoot = new Date().getTime();
	Image image;
	CSocket socket;
	public MyPlayer(CSocket socket) {
		image = new ImageIcon("assets/PNG/playerShip3_blue.png").getImage();
		this.socket = socket;
	}
	@Override
	public void update(float delta, Game game) {	
		if(game.getGameState().getKeyboard()[KeyEvent.VK_A]) {
			setAngle((float) (getAngle() - Math.PI / 72f));
		}
		if(game.getGameState().getKeyboard()[KeyEvent.VK_D]) {
			setAngle((float) (getAngle() + Math.PI / 72f));
		}
		if(game.getGameState().getKeyboard()[KeyEvent.VK_W]) {
			setMotion(500f);
		}
		if(game.getGameState().getKeyboard()[KeyEvent.VK_S]) {
			setMotion(getMotion() * 0.9f);
		}
		//
		
		float speed = getMotion() * delta;
		setPosX((float) (getPosX() + speed * Math.cos(getAngle() - Math.PI / 2)));
		setPosY((float) (getPosY() + speed * Math.sin(getAngle() - Math.PI / 2)));
		setMotion(getMotion() * 0.99f);
		
		// send packet
		socket.sendPacket(new CPacketPlayerPosition(getPosX(), getPosY(), getAngle()));
		//
		if(game.getGameState().getKeyboard()[KeyEvent.VK_SPACE]) {
			long time = new Date().getTime();
			if(time - lastShoot > 10) {
				float angle = (float) (getAngle() - Math.PI / 2);
				//float moX = (float) (Math.cos(angle) * 1000f);
				//float moY = (float) (Math.sin(angle) * 1000f);
				float delX = (float) (Math.cos(angle) * 100f + getPosX());
				float delY = (float) (Math.sin(angle) * 100f + getPosY());
				//bullets.add(new Particle(delX, delY, moX, moY, 500));
				socket.sendPacket(new CPacketShoot(delX, delY, angle));
				lastShoot = time;
			}			
		}
		for(Entity e: game.getWorld().getEntities()) {
			if(e instanceof Particle) {
				if(dist(this, (Particle) e) <= 50) {
					socket.sendPacket(new CPacketDamage(e.getId()));
				}
			}
		}
	}
	public float dist(Player p, Particle b) {
		float dx = p.getPosX() - b.getPosX();
		float dy = p.getPosY() - b.getPosY();
		return (float) Math.sqrt(dx*dx + dy*dy);
	}
	@Override
	public void render(Graphics2D g2d, Game game) {	
		g2d.rotate(getAngle(), getPosX(), getPosY());		
		g2d.drawImage(image, (int) getPosX()-49, (int) getPosY()-49, null);
		g2d.drawOval((int) getPosX()-50, (int) getPosY()-50, 100, 100);
		//g2d.drawLine((int) getPosX(), (int) getPosY(), (int) (getPosX() + 0), (int) (getPosY() - 100));
		g2d.rotate(-getAngle(), getPosX(), getPosY());
		super.render(g2d, game);
	}
} 
