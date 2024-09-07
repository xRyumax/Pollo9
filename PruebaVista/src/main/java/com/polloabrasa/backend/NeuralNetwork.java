package com.polloabrasa.backend;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private List<Layer> layers;

    public NeuralNetwork(int... neuronsPerLayer) {
        layers = new ArrayList<>();
        for (int i = 0; i < neuronsPerLayer.length; i++) {
            layers.add(new Layer(neuronsPerLayer[i], i == 0 ? 0 : neuronsPerLayer[i - 1]));
        }
    }

    public double[] feedForward(double[] inputs) {
        double[] outputs = inputs;
        for (Layer layer : layers) {
            outputs = layer.feedForward(outputs);
        }
        return outputs;
    }

    public void train(double[][] inputSet, double[][] expectedOutputSet, int epochs) {
        for (int i = 0; i < epochs; i++) {
            for (int j = 0; j < inputSet.length; j++) {
                double[] predictedOutput = feedForward(inputSet[j]);
                backpropagate(predictedOutput, expectedOutputSet[j]);
            }
        }
    }

    private void backpropagate(double[] predictedOutput, double[] expectedOutput) {
        double[] error = calculateError(predictedOutput, expectedOutput);
        for (int i = layers.size() - 1; i >= 0; i--) {
            error = layers.get(i).backpropagate(error);
        }
    }

    private double[] calculateError(double[] predictedOutput, double[] expectedOutput) {
        double[] error = new double[predictedOutput.length];
        for (int i = 0; i < predictedOutput.length; i++) {
            error[i] = expectedOutput[i] - predictedOutput[i];
        }
        return error;
    }
}

