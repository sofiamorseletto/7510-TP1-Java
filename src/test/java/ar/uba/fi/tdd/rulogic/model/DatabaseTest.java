package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {
    @Test
    public void parseDbShouldCreateCollectionOfStatements(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Assert.assertTrue(db.getDb().containsKey("hijo"));
        Assert.assertEquals("hijo", db.getDb().get("hijo").get(0).getName());
    }

    @Test
    public void parseLineShouldAddFactToDbCollection(){
        Database db = Database.getInstance();
        db.parseLine("varon(juan).");

        Assert.assertTrue(db.getDb().containsKey("varon"));
    }

    @Test
    public void checkQueryFactVaronShouldReturnTrue(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Assert.assertTrue(db.checkQuery("varon(pedro)"));
    }

    @Test
    public void checkQueryFactPadreShouldReturnFalse(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Assert.assertFalse(db.checkQuery("padre(pedro, luis)"));
    }

    @Test
    public void checkQueryRuleHijoShouldReturnTrue(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Assert.assertTrue(db.checkQuery("hijo(pedro, juan)"));
    }

    @Test
    public void checkQueryRuleHijoShouldReturnFalse(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Assert.assertFalse(db.checkQuery("hijo(pedro, luis)"));
    }

    @Test
    public void checkQueryRuleTioShouldReturnTrue(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules.db");

        Assert.assertTrue(db.checkQuery("tio(nicolas, alejandro, roberto)"));
    }
}
