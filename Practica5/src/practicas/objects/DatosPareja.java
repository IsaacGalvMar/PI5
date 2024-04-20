package practicas.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.alg.util.Pair;

import us.lsi.common.Files2;
import practicas.util.DataLoader;

public record DatosPareja(Integer numeroPersonas, List<List<String>> idiomas, List<Integer> edad, List<String> nacionalidad,
						 Map<Integer,List<Integer>> afinidad){
	
	public static DatosPareja crearObjetoDeDatos(String rutaFichero) {
		
		Integer numeroPersonas = 0;
		List<List<String>> idiomas = new ArrayList<List<String>>();
		List<Integer> edad = new ArrayList<Integer>();
		List<String> nacionalidad = new ArrayList<String>();
	    Map<Integer,List<Integer>> afinidad = new HashMap<Integer,List<Integer>>();
		
	    
	    for(String linea:Files2.streamFromFile(rutaFichero).toList()) {
	    	
	    	linea = linea.replace(":", "#");
	    	
	    	List<String> datos = DataLoader.parseLinea(linea);
	    	if(!datos.isEmpty()) {
	    		edad.add( Integer.valueOf(datos.get(0)) );
	    	
	    		idiomas.add( numeroPersonas, datos.stream().skip(1)
	    				  						       .filter(word -> word.contains("(") && ! word.contains("#"))
	    				  						       .map(word -> word.replace("(", "").replace(")", ""))
	    				  						       .toList() );
	    		Integer index = idiomas.get(numeroPersonas).size();
	    	
	    		nacionalidad.add(numeroPersonas, datos.get(index) );
	    	
	    		List<Integer> af = datos.stream().skip(index + 2).map(word -> word.replace("(", "").replace(")", ""))
	    									  .map(word -> word.split("#")[1])
	    									  .map(word ->Integer.valueOf(word))
	    									  .toList();
	    		
	    		afinidad.put(numeroPersonas, af);
	    		
	    		numeroPersonas++;
	    	}
	    }
	    
	    
	    return new DatosPareja(numeroPersonas,idiomas,edad,nacionalidad,afinidad);
	}

}
