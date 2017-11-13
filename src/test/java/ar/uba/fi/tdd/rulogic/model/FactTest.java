package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

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
}
