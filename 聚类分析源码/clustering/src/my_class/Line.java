package my_class;

public class Line {
	int x1;
	int x2;
	int y1;
	int y2;
	int n;

	public Line(int x1, int y1, int x2, int y2, int n) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.n = n;
	}

	public void set(int x1, int y1, int x2, int y2, int n) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.n = n;
	}
}
