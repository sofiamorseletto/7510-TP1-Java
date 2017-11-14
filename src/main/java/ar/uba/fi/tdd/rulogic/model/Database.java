package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Database {
    private static Database ourInstance = new Database();
    private HashMap<String, LinkedList<Statement>> statements;

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {
        statements = new HashMap<String, LinkedList<Statement>>();
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
        List<Fact> ruleFacts = rule.parse(line);

        if(ruleFacts != null && !statements.containsKey(rule.getName())) {
            LinkedList<Statement> rules = new LinkedList<Statement>();
            rules.add(rule);
            statements.put(rule.getName(), rules);
        }else if(ruleFacts != null){
            statements.get(rule.getName()).add(rule); //Ver si esto se actualiza en el map
        }else{
            Fact fact = new Fact();
            List<String> args = fact.parse(line);

            if(args != null && !statements.containsKey(fact.getName())) {
                LinkedList<Statement> facts = new LinkedList<Statement>();
                facts.add(fact);
                statements.put(fact.getName(), facts);
            }else if(args != null){
                statements.get(fact.getName()).add(fact); //Ver si esto se actualiza en el map
            }
//            else
//                throw new Exception(); //Ver de hacer una clase
//            Si base esta mal, que tire exception
        }
    }

    public HashMap<String, LinkedList<Statement>> getDb(){
        return statements;
    }

    public boolean checkQuery(String query){
        Fact parsedQuery = new Fact();
        List<String> args = parsedQuery.parse(query);
        LinkedList<Statement> stats = statements.get(parsedQuery.getName());
        if(stats == null)
            return false;

        for(Statement s : stats){
            if(s.checkQuery(args))
                return true;
        }
        return false;
    }
}
