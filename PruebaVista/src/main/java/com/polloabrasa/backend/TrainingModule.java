package com.polloabrasa.backend;

public class TrainingModule {

    private NeuralNetwork neuralNetwork;

    public TrainingModule(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void train(double[][] inputs, double[][] outputs, int epochs) {
        neuralNetwork.train(inputs, outputs, epochs);
    }
}

