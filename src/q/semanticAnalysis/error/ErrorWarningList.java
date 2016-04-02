package q.semanticAnalysis.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorWarningList {
	private final List<Error> errorList;
	private final List<Warning> warningList;

	public ErrorWarningList() {
		errorList = new ArrayList<Error>();
		warningList = new ArrayList<Warning>();
	}

	public List<Error> getErrorList() {
		return this.errorList;
	}

	public List<Warning> getWarningList() {
		return this.warningList;
	}

	public void addErrorToErrorList(String e) {
		this.errorList.add(new Error(e));
	}

	public void addWarningToWarningList(String warningMessage) {
		this.warningList.add(new Warning(warningMessage));
	}

	public boolean containsError() {
		return !this.errorList.isEmpty() || !this.warningList.isEmpty();
	}

	public String toString() {
		String output = "";
		for (Error e : errorList) {
			output += e.getErrorWarningText().toString();
		}
		output += " \n";
		for (Warning w : warningList) {
			output += w.getErrorWarningText().toString();
		}
		output += " \n";

		return output;
	}
}