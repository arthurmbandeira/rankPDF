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
//        for (Area k : this.areas) {
//            System.out.println(k.id + " - " + k.name);
//        }

        buffer.close();
    }
    
//    public void initTrainings(String pathFolder, String pathFile, int rk) throws FileNotFoundException, IOException {
//        File trainingFolder = new File(pathFolder);
//        File config = new File(pathFile);
//        File[] trainingFiles;
//        BufferedReader buffer;
//
//        String line;
//        Scanner s;
//        
//        String delimiter = ",";
//        buffer = new BufferedReader(new FileReader(config));
//
//        while ((line = buffer.readLine()) != null) {
//            s = new Scanner(line);
//            s.useDelimiter(delimiter);
//
//            String n = s.next();
//            Article art = new Article(n);
//            
//            
//            List<String> auxList = new ArrayList();
//            while (s.hasNext()) {
//                auxList.add(s.next());
//            }
//
//            Area ar;
//            for (Area a : this.areas) {
////                int j = 0;
////                while (j < auxList.size() && ! auxList.get(j).equals(a.id) ) {
////                    j += 1;
////                }
//                ar = null;
//                int i = 0;
//                System.out.println(auxList.size());
//                for (String g : auxList){
////                    System.out.println(i);
////                    System.out.println(g);
//                    if (g.equals(a.id)) {
//                        ar = a;
//                    }
//                }
////                System.out.println(ar);
//                art.addArea(ar);
////                System.out.println(a.id + " j: " + j);
////                if (auxList.contains(a.id)) {
////                    art.addArea(a);
////                } else {
////                    art.addArea(null);
////                }
//        
//            }            
//
//            this.addTrainingArticle(art);
//            s.close();
//        }
//            
//        buffer.close();
//        if (trainingFolder.exists() && trainingFolder.isDirectory()) {
//            trainingFiles = trainingFolder.listFiles();
//            for (Article ar : trainingList) {
//                for (File f : trainingFiles) {
//                    if (f.getName().equals(ar.name)) {
//                        PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
//                        for (Term t : preProc.getTerms()) {
//                            if (t.numof >= rk) {
//                                ar.addTerm(t);
//                                this.addWord(t.termStr);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//    
//    public void initTargets(String pathFolder, String pathFile, int rk) throws FileNotFoundException, IOException {
//        File trainingFolder = new File(pathFolder);
//        File config = new File(pathFile);
//        File[] trainingFiles;
//        BufferedReader buffer;
//
//        String line;
//        Scanner s;
//        
//        String delimiter = ",";
//        buffer = new BufferedReader(new FileReader(config));
//
//        while ((line = buffer.readLine()) != null) {
//            s = new Scanner(line);
//            s.useDelimiter(delimiter);
//
//            String n = s.next();
//            Article art = new Article(n);
//            
//            
//            List<String> auxList = new ArrayList();
//            while (s.hasNext()) {
//                auxList.add(s.next());
//            }
//            Area ar;
//            for (Area a : this.areas) {
////                int j = 0;
////                while (j < auxList.size() && ! auxList.get(j).equals(a.id) ) {
////                    j += 1;
////                }
//                ar = null;
//                int i = 0;
//                for (String g : auxList){
////                    System.out.println(i);
////                    System.out.println(g);
//                    if (g.equals(a.id)) {
//                        ar = a;
//                    }
//                }
////                System.out.println(ar);
//                art.addArea(ar);
////                System.out.println(a.id + " j: " + j);
////                if (auxList.contains(a.id)) {
////                    art.addArea(a);
////                } else {
////                    art.addArea(null);
////                }
//        
//            }
//            this.addTargetArticle(art);
//            s.close();
//        }
//        buffer.close();
//        if (trainingFolder.exists() && trainingFolder.isDirectory()) {
//            trainingFiles = trainingFolder.listFiles();
//            for (Article ar : targetList) {
//                for (File f : trainingFiles) {
//                    if (f.getName().equals(ar.name)) {
//                        PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
//                        for (Term t : preProc.getTerms()) {
//                            if (t.numof >= rk) {
//                                ar.addTerm(t);
//                                this.addWord(t.termStr);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

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
            
//            int i = 0;
//            while (s.hasNext()) {
//                String aux = s.next().trim();
//                for (Area a : this.areas) {
//                    if (aux.equals(a.id)) {
//                        art.addArea(a);
//                        System.out.println(i + " adiciona " + a.id);
////                        System.out.println("adicionou");
//                    } else {
//                        System.out.println(i + " deu null");
//                        art.addArea(null);
//                    }
//                }
//                i++;
//            }
//            i = 0;
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
            
//            while (s.hasNext()) {
//                String aux = s.next().trim();
//                for (Area a : this.areas) {
//                    if (aux.equals(a.id)) {
//                        art.addArea(a);
////                        System.out.println(a.id);
////                        System.out.println("adicionou");
//                    } else {
////                        System.out.println("entrou");
//                        art.addArea(null);
//                    }
//                }
//            }
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
        
        System.out.println("size: " + trainingList.size());
        for(Article art : this.trainingList){
            System.out.println("teste " + art.areas);
            System.out.println("Occ list: " + art.getOccurenceList(columns).size());
            System.out.println("Area list: " + art.getAreaList().size());
            trainingSet.addRow(new DataSetRow(art.getOccurenceList(columns), art.getAreaList()));
        }
        
//        for(Article art : this.trainingList){
//            for (double k : art.getAreaList()){
//                System.out.println("printou " + k);
//            }
//        }
        
        neuralNetwork.learn(trainingSet);
        
        neuralNetwork.save(nnName);
        
    }
    
    public void hitTargets(){
        NumberFormat formatter = new DecimalFormat("#0.00000");
        String[] columns = belongs.toArray(new String[0]);
        
//        System.out.println("target size: " + targetList.size());
        System.out.println("tamanho: " + this.areas.size());
        for (Article art : targetList){
//            System.out.println("vetor: " + art.getAreaList(this.areas.size()).toString());
            neuralNetwork.setInput(art.getOccurenceVector(columns));
//            System.out.println(art.areas.size());
//            for (double i : art.getOccurenceList(columns)){
//                neuralNetwork.setInput(i);
//            }
            neuralNetwork.calculate();

            double[] networkOutput = neuralNetwork.getOutput();
            System.out.print(art.name + ": ");
            
            for (double d : networkOutput) {
                System.out.print(" " + formatter.format(d));
//            System.out.println(networkOutput.length);
            }
            System.out.println("");
        }
        
        
    }
    
    public void hitTargets(String nnName){
        
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(nnName);
        
        String[] columns = belongs.toArray(new String[0]);
        
        for (Article art : targetList){
            for (double i : art.getOccurenceList(columns)){
                neuralNetwork.setInput(i);
            }
        }
        
        neuralNetwork.calculate();
        
        double[] networkOutput = neuralNetwork.getOutput();
        
        for (double d : networkOutput){
            System.out.println(d);
            System.out.println(networkOutput.length);
        }
    }
    
}
