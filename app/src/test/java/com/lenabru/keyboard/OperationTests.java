package com.lenabru.keyboard;

import com.lenabru.keyboard.base.BaseCalculatorTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.ParameterizedRobolectricTestRunner;
import org.robolectric.ParameterizedRobolectricTestRunner.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lena Brusilovski on 29-May 2017.
 */
@RunWith(ParameterizedRobolectricTestRunner.class)
public class OperationTests extends BaseCalculatorTest {

	private OperationTestModel sut;
	private static class OperationTestModel
			extends TestModel{
		int expectedResult;

		public OperationTestModel(int x, int y, Operation op, int expectedResult){
			super(x,y,op);
			this.expectedResult = expectedResult;
		}
	}


	public OperationTests(OperationTestModel sut){
		this.sut = sut;
	}

	@Parameters
	public static Collection parameters() {

		OperationTestModel add = new OperationTestModel(5,5,Operation.PLUS,10);
		OperationTestModel subtract = new OperationTestModel(10,5,Operation.MINUS,5);
		OperationTestModel divide = new OperationTestModel(10,5,Operation.DIV,2);
		OperationTestModel multiply = new OperationTestModel(10,5,Operation.MULTIPLY,50);
		OperationTestModel none = new OperationTestModel(10,5,Operation.NONE,0);
		return Arrays.asList(new OperationTestModel[][]{{add}, {subtract}, {divide},{multiply},{none}});
	}


	@Test
	public void testOperation() throws Exception{
		System.out.println("Testing:"+sut.operation.name());
		int actualResult = sut.operation.perform(calculator,sut.x,sut.y);
		assertThat(actualResult).isEqualTo(sut.expectedResult);
	}

	@Test
	public void testTheCorrectOperationReturns() throws Exception {
		Operation fromGet = Operation.get(sut.operation.bit);
		assertThat(fromGet).isEqualTo(sut.operation);

	}
}
