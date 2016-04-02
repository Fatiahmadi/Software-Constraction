package q.semanticAnalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;





import q.aTree.type.AType;
public class SymTabel {


	private final Map<String, AType> typeSaver; 
	private final Map<String, String> qLabelSaver; 
	
	public SymTabel() {
		this.typeSaver = new HashMap<String, AType>();
		this.qLabelSaver = new HashMap<String, String>();
	}
	// Next method represent the matching of id and type 
	public void saveType(String identifier, AType type) {
		typeSaver.put(identifier, type);
	}
	//every label should be matched to a id. 
	public void saveQLabel(String identifier, String qlabel) {
		qLabelSaver.put(identifier, qlabel);
	}
	
	private Map<String, AType> getTypeSaver() {
		return typeSaver;
	}
	
	public Map<String, String> getQLabelServer() {
		return qLabelSaver;
	}
	
	
	public boolean isDefined(String identifier) {
		return typeSaver.containsKey(identifier);
	}
	
	public boolean isDefinedQLabel(String identifier) {
		return qLabelSaver.containsKey(identifier);
	}
	
	public AType getValue(String identifier) {
		if(isDefined(identifier)) {
			return typeSaver.get(identifier);
		}
		return null;
	}
	public String getQLabelValue(String identifier) {
		if(isDefined(identifier)) {
			return qLabelSaver.get(identifier);
		}
		return null;
	}
	public boolean empty() {
		if(!this.getTypeSaver().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Set<String> getIDkeys() {
		Set<String> keys = typeSaver.keySet();
		return keys;
	}
}