
public class database_Dao {
	private String signal_name;
	private int start_position;
	private int length;
	private float A;
	private float B;
	private String sign;
	public database_Dao(){}
	public database_Dao(String signal_name, int start_position, int length, float a, float b, String sign) {
		this.signal_name = signal_name;
		this.start_position = start_position;
		this.length = length;
		A = a;
		B = b;
		this.sign = sign;
	}
	public String getSignal_name() {
		return signal_name;
	}
	public void setSignal_name(String signal_name) {
		this.signal_name = signal_name;
	}
	public int getStart_position() {
		return start_position;
	}
	public void setStart_position(int start_position) {
		this.start_position = start_position;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public float getA() {
		return A;
	}
	public void setA(float a) {
		A = a;
	}
	public float getB() {
		return B;
	}
	public void setB(float b) {
		B = b;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
