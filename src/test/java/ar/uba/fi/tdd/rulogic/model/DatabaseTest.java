package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {
    @Test
    public void parseDbShouldCreateCollectionOfStatements(){
        Database db = Database.getInstance();
        db.parseDb("src/main/resources/rules0.db");

        Assert.assertTrue(db.getDb().containsKey("hijo"));
        Assert.assertEquals("hijo", db.getDb().get("hijo").getName());
    }

    @Test
    public void parseLineShouldAddFactToDbCollection(){
        Database db = Database.getInstance();
        db.parseLine("varon(juan).");

        Assert.assertTrue(db.getDb().containsKey("varon"));
    }
}
