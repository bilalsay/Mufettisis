package dev.demo.Services;

public class OptionalParamter {
	
	private Object paramter1;
	private Object paramter2;
	private Object paramter3;
	
	public OptionalParamter(Object paramter1, Object paramter2, Object paramter3) {
		this.paramter1 = paramter1;
		this.paramter2 = paramter2;
		this.paramter3 = paramter3;
	}
	
	public <T> T getParamter1() {
		return (T) paramter1;
	}
	
	public <T> T getParamter2() {
		return (T) paramter2;
	}
	
	public <T> T getParamter3() {
		return (T) paramter3;
	}
	
}
