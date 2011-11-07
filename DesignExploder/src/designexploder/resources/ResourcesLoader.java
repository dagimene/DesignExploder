package designexploder.resources;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesLoader {

	public static InputStreamReader loadJava(String compilationUnitName) {
		return new InputStreamReader(ResourcesLoader.class.getResourceAsStream("java/"+compilationUnitName+".template"));
	}
	
    public static InputStream loadJavaFile(String compilationUnitName) {
        return ResourcesLoader.class.getResourceAsStream("java/"+compilationUnitName+".template");
    }

}
