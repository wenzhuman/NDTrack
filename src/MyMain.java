import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.PackManager;
import soot.Transform;
import soot.toolkits.graph.ExceptionalUnitGraph;

public class MyMain {
	public static void main(String[] args) {
		
		
		PackManager.v().getPack("jtp")
				.add(new Transform("jtp.InformationFlowAnalysis", new BodyTransformer() {

					protected void internalTransform(Body body, String phase,
							Map options) {
						new InformationFlowAnalysis(new ExceptionalUnitGraph(body));
						G.v().out.println("test print"+ body.getMethod());
					}

				}));
       
		soot.Main.main(args);
	}

}