package com.polloabrasa.backend;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neurons;

    public Layer(int numberOfNeurons, int numberOfInputsPerNeuron) {
        neurons = new ArrayList<>();
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons.add(new Neuron(numberOfInputsPerNeuron));
        }
    }

    public double[] feedForward(double[] inputs) {
        double[] outputs = new double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            outputs[i] = neurons.get(i).activate(inputs);
        }
        return outputs;
    }

    public double[] backpropagate(double[] error) {
        double[] nextError = new double[neurons.get(0).getWeights().length];
        for (int i = 0; i < neurons.size(); i++) {
            double[] neuronError = neurons.get(i).backpropagate(error[i]);
            for (int j = 0; j < neuronError.length; j++) {
                nextError[j] += neuronError[j];
            }
        }
        return nextError;
    }
}

