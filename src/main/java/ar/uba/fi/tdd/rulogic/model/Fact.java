package ar.uba.fi.tdd.rulogic.model;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// La que seria la clase LEAF
public class Fact extends Statement{
    private String pattern = "[\\s]*?([a-zA-Z]*)\\(((.*[\\w\\s$]*),?[\\s]*(.*[\\w\\s$]*))\\).?";
    private List<String> args;

    public Fact(){

        args = new LinkedList<String>();
    }

    public List<String> parse(String statement){
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(statement);

        if(!m.find())
            return null;

        name = m.group(1);

        for(int i = 3; i <= m.groupCount(); ++i)
            args.add(m.group(i));

        return args;
    }
}
