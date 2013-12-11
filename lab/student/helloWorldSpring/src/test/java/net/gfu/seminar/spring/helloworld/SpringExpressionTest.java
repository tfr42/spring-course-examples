package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEl() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		String message = (String) exp.getValue();
		assertEquals("Hello World!", message);
	}
	
	@Test 
	public void testElParse(){
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("new java.text.SimpleDateFormat('dd.MM.yyyy').parse('11.11.2011')");
		Date date = (Date) exp.getValue();
		Calendar calendarElevenNov2011 = Calendar.getInstance();
		calendarElevenNov2011.set(Calendar.HOUR_OF_DAY, 0);
		calendarElevenNov2011.set(Calendar.MINUTE, 0);
		calendarElevenNov2011.set(Calendar.SECOND, 0);
		calendarElevenNov2011.set(Calendar.MILLISECOND, 0);
		calendarElevenNov2011.set(Calendar.DAY_OF_MONTH, 11);
		calendarElevenNov2011.set(Calendar.MONTH, 10);
		calendarElevenNov2011.set(Calendar.YEAR, 2011);
		assertEquals(calendarElevenNov2011.getTime(), date);
	}

}
