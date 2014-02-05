package uk.ac.cam.md636.tick4;

public class PatternFormatException extends Exception {
	private String msg;
	public PatternFormatException(String message) {
		this.setMsg(message);
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
