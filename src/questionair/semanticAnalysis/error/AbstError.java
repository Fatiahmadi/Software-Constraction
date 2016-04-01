package questionair.semanticAnalysis.error;

public abstract class AbstError {
	private final String error;

	public AbstError(String e) {
		this.error = e;
	}

	public String getErrorWarningText() {
		return this.error;
	}

	public String toString() {
		return error;
	}

}
