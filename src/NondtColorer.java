import java.awt.List;
import java.util.Iterator;
import java.util.Map;

import dk.brics.soot.transformations.VeryBusyExpsTagger;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.RefLikeType;
import soot.Value;
import soot.ValueBox;
import soot.jimple.Stmt;
import soot.jimple.toolkits.annotation.nullcheck.NullnessAnalysis;
import soot.tagkit.ColorTag;
import soot.tagkit.StringTag;
import soot.toolkits.graph.CompleteUnitGraph;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.scalar.ArraySparseSet;
import soot.toolkits.scalar.FlowSet;

public class NondtColorer extends BodyTransformer {
	public static final String PHASE_NAME = "NondtColorer";
	public static final String TAG_TYPE = "NonDeterminism";
	private static NondtColorer instance = new NondtColorer();

	private NondtColorer() {
	}

	public static NondtColorer v() {
		return instance;
	}

	protected void internalTransform(Body b, String phaseName, Map options) {
		SimpleNonDTVariable simpleNonDT = new SimpleNonDTVariable(
				new ExceptionalUnitGraph(b));
		Iterator it = b.getUnits().iterator();

		while (it.hasNext()) {
			Stmt s = (Stmt) it.next();
			
			Iterator usesIt = simpleNonDT.getNonDTVariableAfter(s).iterator();
			FlowSet inSetValues = new ArraySparseSet();
			while (usesIt.hasNext()) {
				Value inSetValue = ((ValueBox) usesIt.next()).getValue();
				//System.out.println("in set element" + inSetValue);
				inSetValues.add(inSetValue);
			}

			Iterator useBoxIt = s.getUseAndDefBoxes().iterator();
			while (useBoxIt.hasNext()) {
				s.addTag(new StringTag(TAG_TYPE));
				ValueBox use = (ValueBox) useBoxIt.next();
				if (inSetValues.contains(use.getValue())) {
					use.addTag(new ColorTag(ColorTag.RED, TAG_TYPE));

				}
			}
		}

	}

}
