package questionair.semanticAnalysis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class IdentifierList {
	
	    private final Set<String> id;

	    public IdentifierList()
	    {
	        this.id = new HashSet<>();
	    }

	    public void add(String s)
	    {
	        this.id.add(s);
	    }

	    public void addAll(Collection<String> c)
	    {
	        this.id.addAll(c);
	    }

	 //   public int size()
	  //  {
	   //     return this.id.size();
	   // }

}    
	

