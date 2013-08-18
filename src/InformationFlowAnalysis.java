import java.util.Iterator;
import java.util.List;

import soot.G;
import soot.IdentityUnit;
import soot.Local;
import soot.Unit;
import soot.Value;
import soot.ValueBox;
import soot.baf.Inst;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.Stmt;
import soot.jimple.internal.JAssignStmt;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ArraySparseSet;
import soot.toolkits.scalar.FlowSet;
import soot.toolkits.scalar.ForwardFlowAnalysis;
import soot.util.Chain;

public class InformationFlowAnalysis extends ForwardFlowAnalysis {
	FlowSet ndLocals;

	public InformationFlowAnalysis(UnitGraph g) {
		super(g);
		ndLocals = new ArraySparseSet();
		doAnalysis();
	}

	protected Object entryInitialFlow() {
		FlowSet ret = new ArraySparseSet();
		ndLocals.copy(ret);
		return ret;
	}

	protected Object newInitialFlow() {
		return new ArraySparseSet();
	}

	protected void flowThrough(Object in, Object unit, Object out) {

		FlowSet inSet = (FlowSet) in;
		// System.out.println("inSet\t" + inSet);
		FlowSet outSet = (FlowSet) out;
		// System.out.println("outSet\t" + outSet);
		Unit s = (Unit) unit;
		System.out.println("unit\t" + s);
		Iterator<ValueBox> iterInSet = inSet.iterator();
		FlowSet inSetValues = new ArraySparseSet();

		inSet.copy(outSet);
		while (iterInSet.hasNext()) {
			Value inSetValue = iterInSet.next().getValue();
			// System.out.println("in set element" + inSetValue);
			inSetValues.add(inSetValue);
		}

		if (s instanceof AssignStmt) {
			if (unit instanceof AssignStmt) {
				Value rightOp = ((AssignStmt) unit).getRightOp();
				if (rightOp instanceof InvokeExpr) {
					InvokeExpr invokeExpr = (InvokeExpr) rightOp;
					String className = invokeExpr.getMethod()
							.getDeclaringClass().toString();
					String MethodName = invokeExpr.getMethod().getName();
					if (className.equals("java.util.Random")
							&& MethodName.equals("nextInt")) {
						G.v().out.println("left hand "
								+ ((AssignStmt) unit).getLeftOpBox());
						ndLocals.add(((AssignStmt) unit).getLeftOpBox());
						outSet.add(((AssignStmt) unit).getLeftOpBox());
					}

					G.v().out.println("class name" + className + "method name"
							+ MethodName + "signature" + invokeExpr.toString());
					if (invokeExpr
							.toString()
							.contains(
									"<java.util.HashSet: java.util.Iterator iterator()>")) {
						G.v().out.println("left hand "
								+ ((AssignStmt) unit).getLeftOpBox());
						ndLocals.add(((AssignStmt) unit).getLeftOpBox());
						outSet.add(((AssignStmt) unit).getLeftOpBox());
					}

				}
			}
			Iterator<ValueBox> iterRightBox = ((AssignStmt) s).getUseBoxes()
					.iterator();
			boolean rightInSet = false;
			while (iterRightBox.hasNext()) {
				ValueBox righBox = iterRightBox.next();
				Value righBoxValue = righBox.getValue();
				ValueBox leftBox = (((AssignStmt) s).getLeftOpBox());
				if (inSetValues.contains(righBoxValue)) {
					rightInSet = true;
					ndLocals.add(leftBox);
					outSet.add(leftBox);
					G.v().out.println("add " + leftBox);
				}
			}
			if (!rightInSet) {
				
				Value leftHandValue = ((AssignStmt) s).getLeftOpBox()
						.getValue();
				System.out.println("HERE"+leftHandValue);
				if (inSetValues.contains(leftHandValue)) {
					Iterator<ValueBox> iterInSet2 = inSet.iterator();
					while(iterInSet2.hasNext())
					{
						ValueBox removeVB = iterInSet2.next();
						if(removeVB.getValue().equivTo(leftHandValue))
						{
							outSet.remove(removeVB);
							G.v().out.println("!!!!remove " + removeVB);
							System.out.println("!!!!!remove " + removeVB);
						}
					}
					
				}
			}
		}
	}

	protected void merge(Object in1, Object in2, Object out) {
		FlowSet outSet = (FlowSet) out;
		FlowSet inSet1 = (FlowSet) in1;
		FlowSet inSet2 = (FlowSet) in2;
		inSet1.union(inSet2, outSet);
	}

	protected void copy(Object source, Object dest) {
		FlowSet sourceSet = (FlowSet) source;
		FlowSet destSet = (FlowSet) dest;
		sourceSet.copy(destSet);
	}
}
