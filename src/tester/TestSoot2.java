package tester;

import java.util.Random;

public class TestSoot2 {
	public static int r;
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		Random d = new Random();
		b = d.nextInt();
		d.nextInt();
		a = a + d.nextInt();
		double e = b/3;
		TestSoot2.r = b;
		a= 4;
		System.out.println("TestSoot2.r"+TestSoot2.r);
	}

}
