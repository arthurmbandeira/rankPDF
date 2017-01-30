/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.io.IOException;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;
import preprocessingpdf.PreProcessingPDF;

/**
 *
 * @author arthur
 */
public class RankPDF {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    
    public static void main(String[] args) throws IOException, Exception {
        
        RankPDFManager manager = new RankPDFManager();
        int j = 0;
        int rank = Integer.parseInt(args[0]);

        manager.initAreas(args[1]);
        manager.initTrainings(args[2], args[3], rank);
        manager.initTargets(args[4], args[5], rank);
        
//        System.out.println(manager.belongs.size());

        manager.prepareTraining("jarvis.nnet");
        manager.hitTargets();
//        manager.hitTargets("jarvis.nnet");
        
//        System.out.println(manager.areas.size());

        
//        NeuralNetwork neuralNetwork;    
//        neuralNetwork = new Perceptron(2,1);
//        DataSet trainingSet = new DataSet(2,1);   
//        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
//        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
//        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
//        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));
//        
//        neuralNetwork.learn(trainingSet);
//        
//        neuralNetwork.save("or_perceptron.nnet");
//        
//        // load the saved network
//        NeuralNetwork redeNeural = NeuralNetwork.createFromFile("or_perceptron.nnet");
//        // set network input
//        redeNeural.setInput(0, 0);
//        // calculate network
//        redeNeural.calculate();
//        // get network output
//        double[] networkOutput = redeNeural.getOutput();
//        
//        for (double d : networkOutput){
//            System.out.println(d);
//            System.out.println(networkOutput.length);
//        }
        
    }
    
}
