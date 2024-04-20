package practicas.util;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

import us.lsi.common.Matrix;

public class DataLoader {

	public static List<String> parseLista(String text,String div){
		return List.of(text.split(div));
	}
	
	public static String leerObjeto(String text) {
		return (text.contains(":"))?text.split(":")[1]:text.split("->")[1];
	}
	
	public static String leerValor(String text) {
		return text.split("=")[1];
	}
	
	
	
	
	
	
	public static List<String> parseLinea(String linea) {
		List<String> res = new ArrayList<String>();
		
		if( linea.startsWith("//") ) 
			res = List.of();
		else if(linea.contains(";")){
			
			res = parseLineaPuntoComa(linea);
			
		}else if(linea.contains(":")) {
			
			res = parseLineaDosPuntos(linea);
			
		}
		
		return res;
	}
	
	private static List<String> parseLineaPuntoComa(String linea){
		List<String> res = new ArrayList<String>();
		
		List<String> objs = parseLista( leerObjeto(linea) ,";");
		res = objs.stream()
						   .map(obj -> leerValor(obj))
						   .flatMap(obj -> (obj.contains(","))? DataLoader.parseLista(obj, ",").stream():
							   									List.of(obj).stream())
						   .collect(Collectors.toList());
		
		return res;
	}
	
	private static List<String> parseLineaDosPuntos(String linea){
		List<String> res;
		
		res = List.of( linea.split(":") );
		
		return res;
	}
	
	
	
	
	public static Matrix<Binary> generarMatrizBinaria(Integer tamañoColumnas, Integer tamañoFilas, Map<Integer,List<Integer>> datos){
		Matrix<Binary> res = Matrix.of(tamañoFilas, tamañoColumnas, Binary.of(0));
		
		datos.forEach( (key, value) -> {
			value.forEach( val -> {
					res.set(key, val, Binary.of(1));
			});
		});
		
		return res;
	}
	
	
	
	public static Matrix<Binary> generarMatrizCuadradaBinaria(Integer tamaño,List<Integer> listaDeDatos){
		Matrix<Binary> res = Matrix.of(tamaño, tamaño, Binary.of(0));
				
		for(int i = 0; i<=tamaño-1;i++) {
			for(Integer dato: listaDeDatos) {
		
				res.set(i, dato, Binary.of(1));
	
			}
	
		}
		
		return res;
	}
}
