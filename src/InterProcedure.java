import java.util.Iterator;

import soot.Scene;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Targets;

public class InterProcedure {
	public static void main(String[] args) {
		CallGraph cg = Scene.v().getCallGraph();
	}
	// Iterator targets =
	// new Targets(cg.edgesOutOf(src));
	// while( targets.hasNext() ) {
	// SootMethod tgt =
	// (SootMethod) targets.next();
	// System.out.println( ""+
	// src+" may call "+tgt );
}
