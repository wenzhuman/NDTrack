import java.util.Iterator;
import java.util.List;
import java.util.Random;
import soot.Body;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.toolkits.graph.CompleteUnitGraph;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.FlowSet;

public class RunInformationFlowAnalysis {
	public static void main(String[] args) {
		SootClass sClass = Scene.v().loadClassAndSupport("tester.TestSoot");
		sClass.setApplicationClass();
		Iterator<SootMethod> methodIt = sClass.getMethods().iterator();
		while (methodIt.hasNext()) {
			SootMethod m = (SootMethod) methodIt.next();
			// Retrieve the method and its body
			// SootMethod m = c.getMethodByName("main");
			Body b = m.retrieveActiveBody();
			UnitGraph g = new CompleteUnitGraph(b);
			SimpleNonDTVariable simpleNonDT = new SimpleNonDTVariable(g);
			Iterator<Unit> unitIterator = g.iterator();
			while (unitIterator.hasNext()) {
				Unit u = (Unit) unitIterator.next();
				List before = (List) simpleNonDT.getNonDTVariableBefore(u);
				
			    List after = (List) simpleNonDT.getNonDTVariableAfter(u);	
			}
			
		}
	}
}
