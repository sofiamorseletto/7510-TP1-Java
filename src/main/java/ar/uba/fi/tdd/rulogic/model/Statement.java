package ar.uba.fi.tdd.rulogic.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Statement {
    protected String name;

    public String getName(){
        return name;
    }

    public boolean checkQuery(List<String> arguments){ return true; }

}
