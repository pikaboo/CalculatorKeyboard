package com.lenabru.keyboard;

import com.lenabru.keyboard.base.BaseRobolectricTestCase;
import com.lenabru.keyboard.nativeinterface.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Robolectric load native code test
 */
@RunWith(RobolectricTestRunner.class)
public class CalculatorTests
		extends BaseRobolectricTestCase {

	private Calculator sut;

	@Before
	public void setUp() {
		sut = new Calculator();
	}

	@Test
	public void testAddition()
			throws Exception {
		int expectedResult = 10;
		int x = 5;
		int y = 5;
		int actualResult = sut.add(x, y);
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void testSubtraction()
			throws Exception {
		int expectedResult = 5;
		int x = 10;
		int y = 5;
		int actualResult = sut.subtract(x, y);
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void testMult()
			throws Exception {
		int expectedResult = 50;
		int x = 10;
		int y = 5;
		int actualResult = sut.multiply(x, y);
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void testDiv()
			throws Exception {
		int expectedResult = 5;
		int x = 10;
		int y = 2;
		float actualResult = sut.divide(x, y);
		assertThat(actualResult).isEqualTo(expectedResult);
	}
}