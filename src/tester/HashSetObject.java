package tester;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class HashSetObject {

	public static void main(String[] argv) {
		HashSet<T> m;
		m = new HashSet<T>();
		for (int i = 0; i < 10; i++) {
			m.add(new T(Integer.toString(i)));
		}

		for (T t : m) {
			System.out.println(t.s);
		}

	}
}

class T {
	String s;

	public T(String s) {
		this.s = s;
	}
}
