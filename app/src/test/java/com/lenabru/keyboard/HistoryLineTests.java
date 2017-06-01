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
public class HistoryLineTests extends BaseCalculatorTest{

	private HistoryLineModel sut;

	private static class HistoryLineModel extends TestModel{

		private String expectedResult;
		private String expectedDisplay;
		public HistoryLineModel(int x, int y, Operation op, String expectedResult, String expectedDisplay){
			super(x,y,op);
			this.expectedResult = expectedResult;
			this.expectedDisplay = expectedDisplay;
		}
	}

	public HistoryLineTests(HistoryLineModel sut){
		this.sut = sut;
	}

	@Parameters
	public static Collection parameters() {

		HistoryLineModel add = new HistoryLineModel(5,5,Operation.PLUS,"10","5 + 5");
		HistoryLineModel subtract = new HistoryLineModel(10,5,Operation.MINUS,"5", "10 - 5");
		HistoryLineModel divide = new HistoryLineModel(10,5,Operation.DIV,"2","10 / 5");
		HistoryLineModel multiply = new HistoryLineModel(10,5,Operation.MULTIPLY,"50","10 * 5");
		return Arrays.asList(new HistoryLineModel[][]{{add}, {subtract}, {divide},{multiply}});
	}


	@Test
	public void testResult() throws Exception{
		System.out.println("Testing:"+sut.operation.name());
		HistoryLine line = new HistoryLine(sut.x,sut.y,sut.operation);
		String actualResult = line.getOperation().perform(calculator,sut.x,sut.y)+"";
		assertThat(actualResult).isEqualTo(sut.expectedResult);
	}

	@Test
	public void testDisplay() throws Exception{
		System.out.println("Testing:"+sut.operation.name());
		HistoryLine line = new HistoryLine(sut.x,sut.y,sut.operation);
		String actualResult = line.display();
		assertThat(actualResult).isEqualTo(sut.expectedDisplay);
	}

	@Test
	public void testClear() throws Exception {
		HistoryLine line = new HistoryLine(sut.x,sut.y,sut.operation);
		line.setResult(line.getOperation().perform(calculator,line.getX(),line.getY()));
		assertThat(line.getResult()).isNotEqualTo(0);
		assertThat(line.getX()).isNotEqualTo(0);
		assertThat(line.getY()).isNotEqualTo(0);
		line.clear();
		assertThat(line.getResult()).isEqualTo(0);
		assertThat(line.getX()).isEqualTo(0);
		assertThat(line.getY()).isEqualTo(0);
	}
}
