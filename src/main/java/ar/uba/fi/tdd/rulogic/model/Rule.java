package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// La que seria la clase COMPOSITE
public class Rule extends Statement{
    private String pattern = "[\\s]*?([a-zA-Z]*)(\\((.*[\\w\\s,$]*)\\)) :- (.*)";
    private List<Fact> facts;

    public Rule(){
        facts = new LinkedList<Fact>();
    }

    public List<Fact> parse(String statement){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(statement);

        if(!m.find())
            return null;

        name = m.group(1);

        Pattern r2 = Pattern.compile("[\\s]?([\\sa-zA-Z]*\\([\\sa-zA-Z,]*\\))");
        Matcher m2 = r2.matcher(m.group(4));

        while (m2.find()) {
            Fact fact = new Fact();
            fact.parse(m2.group(1));
            facts.add(fact);
        }

        return facts;
    }
}
