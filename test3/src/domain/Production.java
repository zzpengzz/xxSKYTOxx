package domain;


/*
 * ²úÉúÊ½


 */
public class Production {
	
	private String left;
	private String right;
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public Production(String left, String right) {
		super();
		this.left = left;
		this.right = right;
	}
	@Override
	public String toString() {
		return left+"->"+right;
	}
	
}
