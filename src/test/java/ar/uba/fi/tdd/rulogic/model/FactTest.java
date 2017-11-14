package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FactTest {

    @Test
    public void parseShouldCreateFactsName(){
        Fact fact = new Fact();
        fact.parse("padre(juan, pedro).");

        Assert.assertEquals("padre", fact.getName());
    }

    @Test
    public void parseShouldCreateArgumentListWithSingleArgument(){
        Fact fact = new Fact();
        List<String> args = fact.parse("varon(juan).");

        Assert.assertEquals("juan", args.get(0));
    }

    @Test
    public void parseShouldCreateArgumentListWithMultipleArguments(){
        Fact fact = new Fact();
        List<String> args = fact.parse("padre(juan, pedro).");

        Assert.assertEquals("juan", args.get(0));
        Assert.assertEquals("pedro", args.get(1));
    }

    @Test
    public void getModArgsShouldReturnAListOfModifiedArguments(){
        Fact fact = new Fact();
        fact.parse("padre(X, Y).");
        Fact fact2 = new Fact();
        fact2.parse("padre(juan, pedro).");
        HashMap<String, String> refToArgs = new HashMap<String, String>();
        refToArgs.put("X", "juan");
        refToArgs.put("Y", "pedro");

        List<String> modArgs = fact.getModifiedArgs(refToArgs);

        Assert.assertEquals("juan", modArgs.get(0));
        Assert.assertEquals("pedro", modArgs.get(1));
        Assert.assertTrue(fact2.checkQuery(modArgs));
    }

    @Test
    public void checkQueryPadreShouldReturnTrue(){
        Fact fact = new Fact();
        fact.parse("padre(juan, pedro).");
        LinkedList<String> args2 = new LinkedList<>();
        args2.add("juan");
        args2.add("pedro");

        Assert.assertTrue(fact.checkQuery(args2));
    }

    @Test
    public void checkQueryDbShouldReturnTrue(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Fact fact = new Fact();
        List<String> args = fact.parse("padre(juan, pedro).");

        Assert.assertTrue(fact.checkQuery(args));
    }
}
