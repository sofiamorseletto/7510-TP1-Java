package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// La que seria la clase COMPOSITE
public class Rule extends Statement{
    private String pattern = "[\\s]*?([a-zA-Z]*)(\\((.*[\\w\\s,$]*)\\)) :- (.*)";
    private List<Fact> facts;
    private String[] references;

    public Rule(){
        facts = new LinkedList<Fact>();
    }

    public List<Fact> parse(String statement){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(statement);

        if(!m.find())
            return null;

        name = m.group(1);
        references = m.group(3).split(",[\\s]*");

        Pattern r2 = Pattern.compile("[\\s]?([\\sa-zA-Z]*\\([\\sa-zA-Z,]*\\))");
        Matcher m2 = r2.matcher(m.group(4));

        while (m2.find()) {
            Fact fact = new Fact();
            fact.parse(m2.group(1));
            facts.add(fact);
        }

        return facts;
    }

    public boolean checkQuery(List<String> arguments){
        int i = 0, j;
        boolean found;
        HashMap<String, LinkedList<Statement>> statements = Database.getInstance().getDb();
        HashMap<String, String> refToArgs = new HashMap<String, String>();
        for(String ref : references){
            refToArgs.put(ref, arguments.get(i));
            ++i;
        }
        for(Fact f : facts){
            if(!statements.containsKey(f.getName()))
                return false;
            j = 0;
            found = false;
            List<String> modArgs = f.getModifiedArgs(refToArgs);
            LinkedList<Statement> stats = statements.get(f.getName());
            while (!found && j < stats.size()){
                if(stats.get(j).checkQuery(modArgs))
                    found = true;
                ++j;
            }
            if(!found)
                return false;
        }
        return true;
    }
}
