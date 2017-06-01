package com.lenabru.keyboard;

import com.lenabru.keyboard.base.BaseCalculatorTest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lena Brusilovski on 01-Jun 2017.
 */
public class HistoryControllerTests
		extends BaseCalculatorTest{

	private HistoryController sut;

	@Before
	public void setUp(){
		super.setUp();
		sut = new HistoryController(calculator);
	}

	@Test
	public void testCalculateWithNoOperationsReturnsNullHistoryLine(){
		HistoryLine result = sut.calculate();

		assertThat(result).isNull();
	}

	@Test
	public void testCalculateWithOneOperandReturnsNullHistoryLine(){
		sut.appendKeyCode("100");
		HistoryLine result = sut.calculate();
		assertThat(result).isNull();
	}

	@Test
	public void testCalculateWithOperationConsidersOperationWithZero(){
		sut.appendKeyCode("100");
		sut.setOperation(Operation.PLUS);
		HistoryLine result = sut.calculate();

		assertThat(result).isNotNull();
	}

	@Test
	public void testCalculateOperationGivesTheCorrectResult(){
		sut.appendKeyCode("100");
		sut.setOperation(Operation.PLUS);
		sut.appendKeyCode("100");
		HistoryLine result = sut.calculate();
		assertThat(result.getResult()).isEqualTo(200);
	}

	@Test
	public void testNonString() throws Exception{
		sut.appendKeyCode("Lena");
		sut.setOperation(Operation.PLUS);
		sut.appendKeyCode("5");
		HistoryLine result = sut.calculate();
		assertThat(sut.getResultString(result)).isEqualToIgnoringCase("5");
	}

	@Test
	public void testAppliedHistory() throws Exception{
		sut.appendKeyCode("5");
		sut.setOperation(Operation.PLUS);
		sut.appendKeyCode("5");
		HistoryLine result = sut.calculate();
		String expectedResult = sut.display();
		assertThat(result.getResult()).isEqualTo(10);
		sut.setOperation(Operation.PLUS);
		sut.appendKeyCode("8");
		HistoryLine result1 = sut.calculate();
		assertThat(result1.getResult()).isEqualTo(18);
		sut.applyHistory(result);
		assertThat(sut.display()).isEqualToIgnoringCase(expectedResult);

	}


}
