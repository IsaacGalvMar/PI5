package practicas.test;

import java.util.stream.Stream;

import practicas.algorithm.HuertoAlgo;
import practicas.objects.DatosHuerto;
import practicas.solutions.HuertoSolution;
import practicas.virtualgraphs.HuertoEdge;
import practicas.virtualgraphs.HuertoVertex;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.VirtualVertex;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio1 {

	// Las constantes se definen para dar mejor formato a los tests
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	
	
	
	
	public static void testEjercicio1() {
		
		System.out.println(ANSI_PURPLE + "--- TEST HUERTOS ---" + ANSI_RESET);
		
		Stream.of(1,2,3).forEach(i -> testData(i,"data/Ejercicio1DatosEntrada"+ i + ".txt"));
		
	}
	
	public static void testData(Integer i, String dataURI) {
	
		DatosHuerto data = DatosHuerto.crearObjetoDeDatos(dataURI);
		EGraph<HuertoVertex,HuertoEdge> graph = EGraph.virtual(HuertoVertex.initialVertex(data), HuertoVertex.goal(),PathType.Last, Type.Max)
					.greedyEdge(HuertoVertex::greedyEdge)
					.heuristic(HuertoVertex::heuristic)
					.build();

		System.out.println(ANSI_GREEN + "--- TEST"+i+" ---" + ANSI_RESET);

		testPDR(graph);
		testAStar(graph);
		testBT(graph);

		//testManualPD(graph);
		
	}
	
	
	private static void testManualPD(EGraph<HuertoVertex, HuertoEdge> graph) {
		
		System.out.println(ANSI_RED + "Test Manual PD" + ANSI_RESET);
		
		HuertoAlgo bt = HuertoAlgo.ofManualPD(graph);
		
		HuertoSolution sol = bt.getSolucion();
		
		printSolucion(sol);
		
	}
	
	private static void testPDR(EGraph<HuertoVertex, HuertoEdge> graph) {
		
		System.out.println(ANSI_RED + "Test PDR" + ANSI_RESET);
		
		HuertoAlgo bt = HuertoAlgo.ofPDR(graph);
		
		HuertoSolution sol = bt.getSolucion();
		
		printSolucion(sol);
		
	}
	
	private static void testAStar(EGraph graph) {
		
		System.out.println(ANSI_RED + "Test AStar" + ANSI_RESET);
		
		HuertoAlgo bt = HuertoAlgo.ofAStar(graph);
		
		HuertoSolution sol = bt.getSolucion();
		
		printSolucion(sol);
		
	}
	
	private static void testBT(EGraph graph) {
		
		System.out.println(ANSI_RED + "Test BT" + ANSI_RESET);
		
		HuertoAlgo bt = HuertoAlgo.ofBT(graph);
		
		HuertoSolution sol = bt.getSolucion();
		
		printSolucion(sol);
	}
	
	
	private static void printSolucion(HuertoSolution sol) {
		System.out.println("Reparto de verduras y huerto en el que es plantada cada una de ellas (si procede):");
		
		sol.huertoVariedad().forEach( (huerto, variedades) -> {
			variedades.forEach(variedad -> {
				System.out.printf("V%d: Huerto %d\n",variedad,huerto);
			});
		});
		
		System.out.printf("Variedades cultivadas: %d", sol.totalVariedades());
	}
}
