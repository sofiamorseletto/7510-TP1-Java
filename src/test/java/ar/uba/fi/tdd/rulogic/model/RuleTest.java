package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
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

    @Test
    public void checkQueryHijoShouldReturnTrue(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Fact parsedQuery = new Fact();
        List<String> args = parsedQuery.parse("hijo(pedro, juan).");
        LinkedList<Statement> stats = db.getDb().get(parsedQuery.getName());


        Assert.assertTrue(stats.get(0).checkQuery(args));
    }

    @Test
    public void checkQueryHijohouldReturnFalse(){
        Database db = Database.getInstance();
        //TODO: Crear archivo y agregar facts en vez de usar este
        db.parseDb("src/main/resources/rules0.db");

        Fact parsedQuery = new Fact();
        List<String> args = parsedQuery.parse("hijo(pedro, luis).");
        LinkedList<Statement> stats = db.getDb().get(parsedQuery.getName());


        Assert.assertFalse(stats.get(0).checkQuery(args));
    }
}
