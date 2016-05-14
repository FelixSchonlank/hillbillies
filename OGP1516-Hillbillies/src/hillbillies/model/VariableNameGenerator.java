package hillbillies.model;

public class VariableNameGenerator {
	
	private static final String prefix = "FELIX_IS_BAAS_";
	private static Integer counter = 0;
	
	public static String getNext () {
		return prefix + counter.toString();
	}
	
}
