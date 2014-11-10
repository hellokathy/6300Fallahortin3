package edu.gatech.seclass.assignment7;

public class MyClass {
	int var=3;
	public void buggyMethod1(int x, boolean cond){
		if (cond||x==0){ //since this is or statement full branch coverage would need to be seen to identify the error
			System.out.println(1337/x);
		} else {
			System.out.println(1337*x);
		}
		
		//System.out.println("");
	};
	
	public void setZero(){
		var=0;
	}
	
	public void buggyMethod2(int x){
		System.out.println(x/var);
	};

}

