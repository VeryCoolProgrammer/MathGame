package com.mygdx.game.battle.examples;

import com.mygdx.game.battle.steps.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleDatabase {
    private List<Example> examples = new ArrayList<Example>();
    private HashMap<String, Integer> mappings = new HashMap<String, Integer>();
    private Example currentExample;

    public ExampleDatabase(){
        initializeExamples();
    }
    private void initializeExamples() {
        addExample(new TrueExample(new ExampleDetails(EXAMPLE_LIST.EXAMPLE_1, "     59-36=       ", 1)));
        addExample(new TrueExample(new ExampleDetails(EXAMPLE_LIST.EXAMPLE_2, "пример 2", 2)));
        addExample(new TrueExample(new ExampleDetails(EXAMPLE_LIST.EXAMPLE_3, "пример 3", 3)));
        addExample(new TrueExample(new ExampleDetails(EXAMPLE_LIST.EXAMPLE_4, "пример 4", 4)));
        addExample(new TrueExample(new ExampleDetails(EXAMPLE_LIST.EXAMPLE_5, "пример 5", 5)));
    }
    public void addExample(Example example){
        examples.add(example);
        mappings.put(example.getName(), examples.size()-1);
    }
    public Example getExample(int index) {
        return examples.get(index).clone();
    }

    /*public ExampleDetails getNextExample(int index){
        Example nextExample = examples.get(index);
        return nextExample;
    }*/
}
