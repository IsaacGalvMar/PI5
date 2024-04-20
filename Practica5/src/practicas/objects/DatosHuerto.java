package practicas.objects;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Matrix;
import practicas.util.Binary;
import practicas.util.DataLoader;

public record DatosHuerto(Integer totalVariedades, Integer totalHuertos, List<Integer> espacioRequerido,
						List<Integer> espacioHuerto, Matrix<Binary> incompatibilidades){
	 
	public static DatosHuerto crearObjetoDeDatos(String rutaFichero) {
		
		Integer totalVariedades = 0;
		Integer totalHuertos = 0;
		
		List<Integer> espacioRequerido =  new ArrayList<Integer>();
		List<Integer> espacioHuerto =  new ArrayList<Integer>();
		
		List<Integer> incompTemporal = new ArrayList<Integer>();
		Matrix<Binary> incompatibilidades;
		
		
		for(String linea : Files2.streamFromFile(rutaFichero).toList()) {
			
			List<String> datos = DataLoader.parseLinea(linea);
			
			if(!datos.isEmpty()) {
				
			
				if( linea.startsWith("H") ) {
				
					totalHuertos++;
				
					espacioHuerto.add( Integer.valueOf(datos.get(0)) );
				
				}else {
				
					totalVariedades++;
				
					espacioRequerido.add( Integer.valueOf(datos.get(0)) );
				
					incompTemporal.addAll( datos.stream().skip(1).map(dato -> dato.replace("V", ""))
					  .map(dato -> Integer.valueOf(dato))
					  .toList());
				}
				
			}
			
		}
		
		incompatibilidades = DataLoader.generarMatrizCuadradaBinaria(totalVariedades,incompTemporal);
		
		return new DatosHuerto(totalVariedades,totalHuertos,espacioRequerido,espacioHuerto,incompatibilidades);
	}
	
	
	
	
}
