package com.lenabru.keyboard;

import android.util.Log;

import com.lenabru.keyboard.nativeinterface.Calculator;

/**
 * Created by Lena Brusilovski on 01-Jun 2017.
 */

public class HistoryController {

	private static String TAG = HistoryController.class.getSimpleName();

	private HistoryLine historyLine;

	private Calculator calc;

	private StringBuilder appender = new StringBuilder();

	public HistoryController() {
		calc = new Calculator();
		historyLine = new HistoryLine(0, 0, Operation.NONE);
	}

	public HistoryController(Calculator calc) {
		this();
		this.calc = calc;
	}

	public HistoryLine calculate() {
		if (historyLine.getOperation() == Operation.NONE) {
			//nothing to set
			return null;
		}

		HistoryLine result = new HistoryLine(historyLine);
		result.setResult(result.getOperation().perform(calc, result.getX(), result.getY()));
		applyHistory(result);

		return result;
	}

	private void clear() {
		appender.delete(0, appender.length());
		historyLine.clear();
	}

	public void setOperation(int operation) {

		Operation op = Operation.get(operation);
		historyLine.setOperation(op);
		appender.delete(0, appender.toString().length());
	}

	public void setOperation(Operation op) {
		setOperation(op.bit);
	}

	public void appendKeyCode(String s) {
		try {
			int num = Integer.parseInt(s);
			append(num);
		} catch (NumberFormatException e) {
			Log.d(TAG, "expected int but found string:" + s);
		}
	}

	private void append(int num) {
		appender.append(num);
		if (historyLine.getOperation() == Operation.NONE) {
			historyLine.setX(appenderToNumber());
		} else {
			historyLine.setY(appenderToNumber());
		}
	}

	private int appenderToNumber() {
		try {
			return Integer.parseInt(appender.toString());
		} catch (NumberFormatException e) {
			Log.d(TAG, "expected number but found string:" + appender.toString());
		}
		return 0;
	}

	public String getResultString(HistoryLine historyLine) {
		return historyLine.getResult() + "";
	}

	public void applyHistory(HistoryLine historyLine) {
		clear();
		appendKeyCode(getResultString(historyLine));
	}

	public String display() {
		return historyLine.display();
	}
}
