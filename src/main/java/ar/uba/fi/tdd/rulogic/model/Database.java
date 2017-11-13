package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Database {
    private static Database ourInstance = new Database();
    private HashMap<String, Statement> statements;

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {
        statements = new HashMap<String, Statement>();
    }

    public void parseDb(String fileName){
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(this::parseLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Ver si hacerlo privado y testear con reflection
    public void parseLine(String line){
        Rule rule = new Rule();
        if(rule.parse(line) != null)
            statements.put(rule.getName(), rule);
        else{
            Fact fact = new Fact();
            if(fact.parse(line) != null)
                statements.put(fact.getName(), fact);
//            else
//                throw new Exception(); //Ver de hacer una clase
//            Si base esta mal, que tire exception
        }
    }

    public HashMap<String, Statement> getDb(){
        return statements;
    }
}
