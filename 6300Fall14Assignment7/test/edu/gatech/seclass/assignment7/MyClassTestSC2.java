package edu.gatech.seclass.assignment7;
import static edu.gatech.seclass.assignment7.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MyClassTestSC2 {

	@Test
	public void test() {
		MyClass test = new MyClass();
		test.setZero();
		test.buggyMethod2(3);
	}
}
