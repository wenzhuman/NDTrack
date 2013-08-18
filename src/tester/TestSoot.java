package tester;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class TestSoot {
	
	public static void main(String[] argv) {
	 HashSet<String> m;
	 LinkedHashSet<String> n;
		m = new HashSet<String>();
		for (int i = 0; i < 10; i++) {
			m.add("" + i);
		}
		n = new LinkedHashSet<String>();
		for (int i = 0; i < 10; i++) {
			n.add("" + i);
		}
		Iterator<String> i = m.iterator();
		Iterator<String> j = n.iterator();
		while (i.hasNext()) {
			String nextT = (String) i.next();
			System.out.print(nextT + ",");
		}
		while (j.hasNext()) {
			String nextT2 = (String) j.next();
			System.out.print(nextT2 + ",");
		}


	}
}
