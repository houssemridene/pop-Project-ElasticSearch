package talan.Elastic.entities;

public class Path {
	
	public String getVirtual() {
		return virtual;
	}
	public Path() {
		super();
	}
	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}
	public String getReal() {
		return real;
	}
	public void setReal(String real) {
		this.real = real;
	}
	private String virtual;
	private String real;

}
