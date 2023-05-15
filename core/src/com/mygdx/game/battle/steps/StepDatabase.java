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
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "56      ", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "13      ", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "28      ", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.SPECIAL, 15, "16      ", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "ответ5", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "ответ6", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "ответ7", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.SPECIAL, 15, "ответ8", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.DEFAULT, 10, "ответ9", STEP_BOOLEAN.WRONG)));
        addStep(new DamageStep(new StepsDetails(STEP_TYPE.SPECIAL, 15, "ответ10", STEP_BOOLEAN.WRONG)));
    }

    public void addStep(Step step){
        steps.add(step);
        mappings.put(step.getName(), steps.size()-1);
    }

    public Step getSteps(int index) {
        return steps.get(index).clone();
    }
}
