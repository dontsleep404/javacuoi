package game;

public class GameState {
	private boolean[] keyboard;
	private boolean[] mouse;
	private int mouseX;
	private int mouseY;
	public GameState() {
		keyboard = new boolean[1000];
		mouse = new boolean[100];
		mouseX = 0;
		mouseY = 0;
	}
	public boolean[] getKeyboard() {
		return keyboard;
	}
	public void setKeyboard(boolean[] keyboard) {
		this.keyboard = keyboard;
	}
	public boolean[] getMouse() {
		return mouse;
	}
	public void setMouse(boolean[] mouse) {
		this.mouse = mouse;
	}
	public int getMouseX() {
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
}
