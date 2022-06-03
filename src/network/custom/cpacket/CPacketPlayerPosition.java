package network.custom.cpacket;

import network.custom.Packet;

public class CPacketPlayerPosition extends Packet{
	private float posX;
	private float posY;
	private float angle;
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public CPacketPlayerPosition(float posX, float posY, float angle) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.angle = angle;
	}
	
}
