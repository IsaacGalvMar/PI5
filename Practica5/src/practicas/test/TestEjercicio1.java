package practicas.test;

import java.util.stream.Stream;

import practicas.objects.DatosHuerto;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.VirtualVertex;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio1 {

	public static void testEjercicio1() {
		
		HuertoVertex initialVertex = HuertoVertex.initialVertex();
		
		Stream.of(1,2,3).map(i -> DatosHuerto.crearObjetoDeDatos("data/Ejercicio1DatosEntrada"+ i + ".txt"))
						.map(data -> EGraph.virtual(initialVertex, HuertoVertex.goal(), PathType.Last, Type.Max)
											.greedyEdge(HuertoVertex::greedyEdge)
											.heuristic(HuertoHeuristic::heuristic)
											.build())
						.forEach(graph -> {
							
							testPDR(graph);
							testAStar(graph);
							testBT(graph);
							
						});
		
	}
	
	private static void testPDR(EGraph<HuertoVertex, HuertoEdge> graph) {
		
		PDR<HuertoVertex, HuertoEdge,SolucionHuerto> sol = PDR.ofGreedy(graph);
		GraphPath<HuertoVertex, HuertoEdge> path = sol.search().get();
		
	}
	
	private static void testAStar(EGraph graph) {
		
	}
	
	private static void testBT(EGraph graph) {
		
	}
}
