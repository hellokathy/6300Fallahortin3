package edu.gatech.seclass.assignment7;
import static edu.gatech.seclass.assignment7.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MyClassTestBC1 {


	@Test
	public void test() {
		MyClass test = new MyClass();
		test.buggyMethod1(0, true);
	}
	@Test
	public void test2() {
		MyClass test = new MyClass();
		test.buggyMethod1(1, false);
	}
	@Test
	public void test3() {
		MyClass test = new MyClass();
		test.buggyMethod1(0, true);
	}
	@Test
	public void test4() {
		MyClass test = new MyClass();
		test.buggyMethod1(0, false);
	}

}
