import java.util.List;

import soot.Unit;

public interface NonDTVariable {
	public List getNonDTVariableBefore(Unit S);

	public List getNonDTVariableAfter(Unit S);
}
