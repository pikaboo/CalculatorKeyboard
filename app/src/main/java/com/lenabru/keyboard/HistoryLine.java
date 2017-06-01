package com.lenabru.keyboard;

/**
 * Created by Lena Brusilovski on 29-May 2017.
 */

public class HistoryLine {

	private int x;
	private int y;
	private int result;

	private Operation operation;

	public HistoryLine(int x, int y, Operation operation) {
		this.x = x;
		this.y = y;
		this.operation = operation;
	}

	public HistoryLine(HistoryLine other){
		this(other.x,other.y,other.operation);
	}

	public String display(){
		return x+" "+operation.type+" "+y;
	}

	public int getResult(){
		return result;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void clear(){
		setX(0);
		setY(0);
		setResult(0);
		setOperation(Operation.NONE);
	}
}
