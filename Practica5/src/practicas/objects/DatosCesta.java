package practicas.objects;

import us.lsi.common.Files2;
import practicas.util.DataLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record DatosCesta(Integer numeroProductos, Integer numeroCategorias, Integer presupuesto,
		Map<Integer,List<Integer>> productoCategoria, List<Integer> precioProducto, List<Integer> valoracionProducto){
	

	
	public static DatosCesta crearObjetoDeDatos(String rutaFichero) {
		
		Integer numeroProductos = 0;
		Integer numeroCategorias = 0 ;
		
		Integer presupuesto = 0;
		List<Integer> precioProducto = new ArrayList<Integer>();
		List<Integer> valoracionProducto = new ArrayList<Integer>();
	
		Map<Integer,List<Integer>> productoCategoria = new HashMap<Integer,List<Integer>>();
		
		for(String linea : Files2.streamFromFile(rutaFichero).toList()) {
			
			if(linea.contains("="))
				presupuesto = Integer.valueOf( DataLoader.leerValor(linea).trim() );
			
			List<String> objs = DataLoader.parseLinea(linea);
			
			if(!objs.isEmpty()) {
				Integer id = Integer.valueOf( objs.get(0) );
				Integer categoria = Integer.valueOf( objs.get(2) );
				Integer valoracion = Integer.valueOf( objs.get(3) );
			
				precioProducto.add(id, Integer.valueOf( objs.get(1) ));
			
				if(productoCategoria.containsKey( categoria )) {
					productoCategoria.get( categoria ).add(id);
				}else {
					productoCategoria.put( categoria, new ArrayList<Integer>());
					productoCategoria.get( categoria ).add(id);
					numeroCategorias++;
				}
				
				
				
				valoracionProducto.add(id, valoracion );
			
				numeroProductos++;
			}
		}
		
		return new DatosCesta(numeroProductos,numeroCategorias,presupuesto,productoCategoria,precioProducto,valoracionProducto);
	}
	
	
}
