package edu.gatech.seclass.assignment7;
import static edu.gatech.seclass.assignment7.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MyClassTestBC2 {

	@Test
	public void test() {
		MyClass test = new MyClass();
		test.buggyMethod2(3);
	}
}
