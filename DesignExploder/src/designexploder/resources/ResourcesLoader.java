package designexploder.resources;

import java.io.InputStreamReader;

public class ResourcesLoader {

	public static InputStreamReader loadJava(String compulationUnitName) {
		return new InputStreamReader(ResourcesLoader.class.getResourceAsStream("java/"+compulationUnitName+".template"));
	}
	
}
