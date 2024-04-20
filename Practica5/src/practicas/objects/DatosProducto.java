package practicas.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.alg.util.Pair;

import us.lsi.common.Files2;
import practicas.util.DataLoader;

public record DatosProducto(Integer tiposProductos, Integer numeroDestinos, List<Integer> cantidadProductoDisponible,
							List<Integer> demandaMinimaDestino, Map<Integer,List<Integer>> costeAlmacenamiento) {

	
	public static DatosProducto crearObjetoDeDatos(String rutaFichero) {
		Integer tiposProductos = 0;
		Integer numeroDestinos = 0;
		List<Integer> cantidadProductoDisponible = new ArrayList<Integer>();
		List<Integer> demandaMinimaDestino = new ArrayList<Integer>();
		Map<Integer,List<Integer>> costeAlmacenamiento = new HashMap<Integer,List<Integer>>();
		
		for(String linea:Files2.streamFromFile(rutaFichero).toList()) {
			
			if(linea.startsWith("P"))
				linea = linea.replace(":", "#");
			
			List<String> datos = DataLoader.parseLinea(linea);
			
			if(!datos.isEmpty()) {
				
				if(linea.startsWith("D")) {
					demandaMinimaDestino.add( Integer.valueOf(datos.get(0)) );
					
					numeroDestinos++;
				}else{
					cantidadProductoDisponible.add( Integer.valueOf(datos.get(0)) );
					costeAlmacenamiento.put(tiposProductos, new ArrayList<Integer>());
					List<Pair<Integer,Integer>> obj = datos.stream().skip(1).map(v -> v.replace("(", "").replace(")", ""))
										  .map(v -> Pair.of(v.split("#")[0], v.split("#")[1]))
										  .map(pair -> Pair.of( Integer.valueOf(pair.getFirst()), Integer.valueOf(pair.getSecond())))
										  .toList();
						
					for(Pair<Integer,Integer> pair:obj)
						costeAlmacenamiento.get(tiposProductos).add(pair.getFirst(),pair.getSecond());
					
					tiposProductos++;
				}
				
			}
			
			
		}
		
		return new DatosProducto(tiposProductos,numeroDestinos,cantidadProductoDisponible,
									demandaMinimaDestino,costeAlmacenamiento);
	}
	
}
