package ar.uba.fi.tdd.rulogic.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// La que seria la clase LEAF
public class Fact extends Statement{
    private String pattern = "[\\s]*?([a-zA-Z]*)[\\s]*?\\([\\s]*((.*[\\w\\s$]*),?[\\s]*(.*[\\w\\s$]*))\\).?";
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
        String[] v = m.group(2).split(",[\\s]*");
        for(int i = 0; i < v.length; ++i)
            args.add(v[i].trim());

        return args;
    }

    public boolean checkQuery(List<String> arguments){
        if(arguments.size() != args.size())
            return false;
        for(int i = 0; i < args.size(); ++i){
            if(arguments.get(i).compareTo(args.get(i)) != 0)
                return false;
        }
        return true;
    }

    public List<String> getModifiedArgs(HashMap<String, String> refToArgs){
        LinkedList<String> modArgs = new LinkedList<>();
        for(String arg : args){
            modArgs.add(refToArgs.get(arg));
        }
        return modArgs;
    }

    public List<String> getArgs(){
        return args;
    }
}
