/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;
import preprocessingpdf.PreProcessingPDF;
import preprocessingpdf.Term;

/**
 *
 * @author arthur
 */
public class RankPDFManager {

    public List<Article> trainingList;
    public List<Article> targetList;

    public List<Area> areas;

    public Set<String> belongs;
    
    public NeuralNetwork neuralNetwork;    
    public DataSet trainingSet;
    
    public RankPDFManager() {
        trainingList = new ArrayList();
        targetList = new ArrayList();
        belongs = new HashSet();
        areas = new ArrayList();
    }

    public void addTrainingArticle(Article a) {
        trainingList.add(a);
    }

    public void addTargetArticle(Article a) {
        targetList.add(a);
    }

    public void addWord(String s) {
        belongs.add(s);
    }

    public void addAreas(Area a) {
        areas.add(a);
    }

    public void initAreas(String path) throws FileNotFoundException, IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(path));

        String line;
        Scanner s;

        String delimiter = "-";

        while ((line = buffer.readLine()) != null) {
            s = new Scanner(line);
            s.useDelimiter(delimiter);

            Area a = new Area(s.next(), s.next().substring(0).trim());
            this.addAreas(a);
            s.close();
        }

        buffer.close();
    }

    public void initTrainings(String pathFolder, String pathFile,  int rk) throws FileNotFoundException, IOException {
        File targetFolder = new File(pathFolder);
        File config = new File(pathFile);
        File[] targetFiles;
        BufferedReader buffer;

        String line;
        Scanner s;

        String delimiter = ",";
        buffer = new BufferedReader(new FileReader(config));

        while ((line = buffer.readLine()) != null) {
            s = new Scanner(line);
            s.useDelimiter(delimiter);

            String n = s.next();
            Article art = new Article(n);
            
            List<String> linha = new ArrayList();
            while (s.hasNext()) {
                linha.add(s.next().trim());
            }
            for (Area a : this.areas) {
                if (linha.contains(a.id)) {
                    art.addArea(a);
                } else {
                    art.addArea(null);
                }
            }
            this.addTrainingArticle(art);
            s.close();
        }
        buffer.close();
        if (targetFolder.exists() && targetFolder.isDirectory()) {
            targetFiles = targetFolder.listFiles();
            for (Article ar : this.trainingList) {
                for (File f : targetFiles) {
                    if (f.getName().equals(ar.name)) {
                        PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
                        for (Term t : preProc.getTerms()) {
                            if (t.numof >= rk) {
                                ar.addTerm(t);
                                this.addWord(t.termStr);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void initTargets(String pathFolder, String pathFile,  int rk) throws FileNotFoundException, IOException {
        File targetFolder = new File(pathFolder);
        File config = new File(pathFile);
        File[] targetFiles;
        BufferedReader buffer;

        String line;
        Scanner s;

        String delimiter = ",";
        buffer = new BufferedReader(new FileReader(config));

        while ((line = buffer.readLine()) != null) {
            s = new Scanner(line);
            s.useDelimiter(delimiter);

            String n = s.next();
            Article art = new Article(n);

            List<String> linha = new ArrayList();
            while (s.hasNext()) {
                linha.add(s.next().trim());
            }
            for (Area a : this.areas) {
                if (linha.contains(a.id)) {
                    art.addArea(a);
                } else {
                    art.addArea(null);
                }
            }
            this.addTargetArticle(art);
            s.close();
        }
        buffer.close();
        if (targetFolder.exists() && targetFolder.isDirectory()) {
            targetFiles = targetFolder.listFiles();
            for (Article ar : this.targetList) {
                for (File f : targetFiles) {
                    if (f.getName().equals(ar.name)) {
                        PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
                        for (Term t : preProc.getTerms()) {
                            if (t.numof >= rk) {
                                ar.addTerm(t);
                                this.addWord(t.termStr);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void prepareTraining(String nnName){
        neuralNetwork = new Perceptron(belongs.size(), this.areas.size());
        
        trainingSet = new DataSet(belongs.size(), this.areas.size());
        
        String[] columns = belongs.toArray(new String[0]);
        
        for(Article art : this.trainingList){
            trainingSet.addRow(new DataSetRow(art.getOccurenceList(columns), art.getAreaList()));
        }
        
        neuralNetwork.learn(trainingSet);
        
        neuralNetwork.save(nnName);
        
    }
    
    public void hitTargets(){
        NumberFormat formatter = new DecimalFormat("#0.00000");
        String[] columns = belongs.toArray(new String[0]);
        for (Article art : targetList){
            neuralNetwork.setInput(art.getOccurenceVector(columns));
            neuralNetwork.calculate();

            double[] networkOutput = neuralNetwork.getOutput();
            System.out.print(art.name + ": ");
            
            for (double d : networkOutput) {
                System.out.print(" " + formatter.format(d));
            }
            System.out.println("");
        }
    }
    
    public void hitTargets(String nnName){
        
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(nnName);
        
        NumberFormat formatter = new DecimalFormat("#0.00000");
        String[] columns = belongs.toArray(new String[0]);
        for (Article art : targetList){
            neuralNetwork.setInput(art.getOccurenceVector(columns));
            neuralNetwork.calculate();

            double[] networkOutput = neuralNetwork.getOutput();
            System.out.print(art.name + ": ");
            
            for (double d : networkOutput) {
                System.out.print(" " + formatter.format(d));
            }
            System.out.println("");
        }
    }
    
}
