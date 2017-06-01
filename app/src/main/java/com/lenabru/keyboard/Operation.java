package com.lenabru.keyboard;

import com.lenabru.keyboard.nativeinterface.Calculator;

/**
 * Created by Lena Brusilovski on 29-May 2017.
 */

public enum Operation {

	NONE("",-1){
		@Override
		public int perform(Calculator calc, int x, int y) {
			return 0;
		}
	},
	PLUS("+", 0) {
		@Override
		public int perform(Calculator calc, int x, int y) {
			return calc.add(x, y);
		}
	}, MINUS("-", 1) {
		@Override
		public int perform(Calculator calc, int x, int y) {
			return calc.subtract(x, y);
		}
	}, MULTIPLY("*", 2) {
		@Override
		public int perform(Calculator calc, int x, int y) {
			return calc.multiply(x, y);
		}
	}, DIV("/", 3) {
		@Override
		public int perform(Calculator calc, int x, int y) {
			return (int) calc.divide(x, y);
		}
	};

	String type;

	int bit;

	Operation(String type, int bit) {
		this.type = type;
		this.bit = bit;
	}

	public abstract int perform(Calculator calc, int x, int y);

	public static Operation get(int bit) {
		for (Operation op : values()) {
			if (op.bit == bit) {
				return op;
			}
		}
		return NONE;//illegal bit
	}
}
