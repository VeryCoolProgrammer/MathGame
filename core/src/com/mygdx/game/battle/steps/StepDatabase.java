package com.mygdx.game.battle.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StepDatabase {
    private List<Step> steps = new ArrayList<Step>();
    private HashMap<String, Integer> mappings = new HashMap<String, Integer>();

    public StepDatabase(){
        initializeSteps();
    }

    private void initializeSteps() {
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "ответ1")));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "ответ2")));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "ответ3")));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.SPECIAL, 15, "ответ4")));
    }

    public void addStep(Step step){
        steps.add(step);
        mappings.put(step.getName(), steps.size()-1);
    }

    /*public Step getSteps(String stepName) {
        return steps.get(mappings.get(stepName)).clone(); ???
    }*/

    public Step getSteps(int index) {
        return steps.get(index).clone();
    }
}