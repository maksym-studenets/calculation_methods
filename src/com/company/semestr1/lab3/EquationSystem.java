package com.company.semestr1.lab3;

/**
 * Created by rostyslav on 28.10.2018.
 */
public class EquationSystem {
    private Equation[] equations;
    private double[] values;

    public EquationSystem(Equation... equations) {
        this.equations = equations.clone();
    }

    public void setInitValues(double... initValues) {
        if (initValues.length != equations.length) {
            throw new IllegalArgumentException("init values count should be the same as equations count");
        }
        this.values = initValues.clone();
    }

    public void nextStep() {
        for (int i = 0; i < equations.length; i++) {
            //values[i] = equations[i].calculate()
        }
    }
}
