package questionair.evaluation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ValueSaver {
	
	private final Map<String, Value> valueSaver; 
	
	public ValueSaver() {
		this.valueSaver = new LinkedHashMap<String, Value>();
	}
	
	public void putValue(String id, Value value) {
		valueSaver.put(id, value);
	}
	public boolean isDefined(String id) {
		return valueSaver.containsKey(id);
	}
	
	public Value getValue(String id) {
		if(isDefined(id)) {
			return valueSaver.get(id);
		}
		return null;
	}
	public Set<String> getIDkeys() {
		Set<String> keys = valueSaver.keySet();
		return keys;
	}
	
	public Map<String, Value> getValueSaver() {
		return valueSaver;
	}
}