import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import soot.Unit;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.FlowSet;

public class SimpleNonDTVariable implements NonDTVariable {
	private Map unitToNonDTAfter;
	private Map unitToNonDTBefore;

	public SimpleNonDTVariable(UnitGraph graph) {
		InformationFlowAnalysis inflowFlowAnalysis = new InformationFlowAnalysis(
				graph);
		unitToNonDTBefore = new HashMap(graph.size() * 2 + 1, 0.7f);
		unitToNonDTAfter = new HashMap(graph.size() * 2 + 1, 0.7f);
		Iterator<Unit> unitIt = graph.iterator();

		while (unitIt.hasNext()) {
			Unit s = (Unit) unitIt.next();

			FlowSet set = (FlowSet) inflowFlowAnalysis.getFlowBefore(s);
			unitToNonDTBefore
					.put(s, Collections.unmodifiableList(set.toList()));

			set = (FlowSet) inflowFlowAnalysis.getFlowAfter(s);
			unitToNonDTAfter.put(s, Collections.unmodifiableList(set.toList()));
		}
	}

	@Override
	public List getNonDTVariableBefore(Unit s) {

		return (List) unitToNonDTBefore.get(s);
	}

	@Override
	public List getNonDTVariableAfter(Unit s) {

		return (List) unitToNonDTAfter.get(s);
	}

}
