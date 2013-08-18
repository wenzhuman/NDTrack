import soot.Main;
import soot.PackManager;
import soot.Transform;
import dk.brics.soot.transformations.VeryBusyExpsTagger;


public class RunColor {
	public static void main(String[] args) {
		PackManager.v().getPack("jtp").add(new
				Transform("jtp." + NondtColorer.PHASE_NAME,
						NondtColorer.v()));
		
		Main.main(args);
	}
}
