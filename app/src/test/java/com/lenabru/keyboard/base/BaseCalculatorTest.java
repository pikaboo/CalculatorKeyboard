package com.lenabru.keyboard.base;

import com.lenabru.keyboard.nativeinterface.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lena Brusilovski on 29-May 2017.
 */
@RunWith(RobolectricTestRunner.class)
public class BaseCalculatorTest extends BaseRobolectricTestCase {

	protected Calculator calculator;
	@Before
	public void setUp(){
		calculator = new Calculator();
	}

	@Test
	public void testCalculatorIsNotNull(){
		assertThat(calculator).isNotNull();
	}
}
