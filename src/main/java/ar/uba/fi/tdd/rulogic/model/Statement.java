package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

// La que seria la clase COMPONENT
public abstract class Statement {
    protected String name;

    public String getName(){
        return name;
    }

    public boolean checkQuery(List<String> arguments){ return true; }

}
