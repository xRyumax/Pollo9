package com.polloabrasa.backend;

import java.util.Random;

public class Neuron {
    private double[] weights;
    private double bias;
    private double output;

    public Neuron(int numberOfInputs) {
        Random rand = new Random();
        weights = new double[numberOfInputs];
        for (int i = 0; i < numberOfInputs; i++) {
            weights[i] = rand.nextDouble() - 0.5;
        }
        bias = rand.nextDouble() - 0.5;
    }

    public double activate(double[] inputs) {
        double sum = 0.0;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        sum += bias;
        output = ActivationFunction.sigmoid(sum);
        return output;
    }

    public double[] backpropagate(double error) {
        double delta = error * ActivationFunction.sigmoidDerivative(output);
        double[] errorForPreviousLayer = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            errorForPreviousLayer[i] = delta * weights[i];
            weights[i] += delta * output;
        }
        bias += delta;
        return errorForPreviousLayer;
    }

    public double[] getWeights() {
        return weights;
    }
}

