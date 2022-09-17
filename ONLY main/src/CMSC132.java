
public class CMSC132 {
	private int a;
	private String b;
	public CMSC132 (int a, String b) {
		this.a = a;
		this.b = b;
	}
	public CMSC132 (CMSC132 c) {
		this.b = c.b;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CMSC132 a = new CMSC132 (2, "s");
		CMSC132 b = new CMSC132 (a);
		System.out.println(a.equals(b) ==  b.equals(a));
	}

}
