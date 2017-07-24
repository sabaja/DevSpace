package a1com.mvc.spittr.constants;

public enum SpittlesViews {
	NUM(20);
	private int num;
	
	private SpittlesViews(int num){
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
}
