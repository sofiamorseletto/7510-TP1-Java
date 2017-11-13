package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RuleTest {

    @Test
    public void ruleShouldBeCreatedWithAName(){
        String line = "hijo(X, Y) :- varon(X), padre(Y, X).";
        Rule rule = new Rule();

        List<Fact> facts = rule.parse(line);

        Assert.assertEquals("hijo", rule.getName());
    }

    @Test
    public void parseShouldCreateAnArrayOfFacts(){
        String line = "hijo(X, Y) :- varon(X), padre(Y, X).";
        Rule rule = new Rule();

        List<Fact> facts = rule.parse(line);

        Assert.assertEquals(2, facts.size());
        Assert.assertEquals("varon", facts.get(0).getName());
        Assert.assertEquals("padre", facts.get(1).getName());
    }
}
