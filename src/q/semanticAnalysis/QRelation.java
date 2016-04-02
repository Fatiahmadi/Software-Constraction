package q.semanticAnalysis;


import java.util.*;

import q.semanticAnalysis.IdentifierList;

  
public class QRelation
{
    private final Map<String, Set<String>> relation;
    
    private IdentifierList loopIds;

    public QRelation()
    {
        this.relation = new HashMap<>();
    }

    public void addQ(String id){
   
        if (!(this.relation.containsKey(id)))
        {
            this.relation.put(id, new HashSet<>());
        }
    }

    public void addR(String q, String r)
    {
        if( !(this.relation.containsKey(q))){
        	 throw new AssertionError();}
        else
        this.relation.get(q).add(r);
    }

    public IdentifierList getLoopIds()
    {
        return this.loopIds;
    }

    public boolean hasloop()
    {
        return this.searchLoop();
    }

    private boolean searchLoop()
    {
        this.loopIds = new IdentifierList();

        for (String id : this.relation.keySet())
        {
            Stack<String> stack = new Stack<>();
            stack.add(id);
            if (this.lookingForLoops(stack))
            {
                return true;
            }
        }

        return false;
    }

    private boolean lookingForLoops(Stack<String> stack)
    {
        Set<String> nextElement = this.getNextElement(stack.lastElement());

        for (String n : nextElement)
        {
            if (this.hasNextElementLoop(stack, n))
            {
                return true;
            }

            stack.push(n);
            if (this.lookingForLoops(stack))
            {
                return true;
            }
            stack.pop();
        }

        return false;
    }

    private boolean hasNextElementLoop(Stack<String> stack, String n)
    {
        String firstElement = stack.firstElement();
        if (firstElement.equals(n))
        {
            this.loopIds.addAll(stack);
            return true;
        }

        return false;
    }
    
  //define   
    private Set<String> getNextElement(String id)
    {
        if (this.isIdUndeclared(id))
        {
            return new HashSet<>();
        }

        return this.relation.get(id);

    }

    private boolean isIdUndeclared(String id)
    {
        return !this.relation.containsKey(id);
    }
}