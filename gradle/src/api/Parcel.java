package api;

public class Parcel {
	public double length;
	public double width;
	public double height;
	public String size;
	
	public String toString() {
		return "{ length: " + this.length + ", width: " + this.width + ", height: " + this.height + ", size: " + this.size + " }";
	}

}
