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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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
    
    public void addWord(String s){
        belongs.add(s);
    }
    
    public void addAreas(Area a){
        areas.add(a);
    }
    
    public void initAreas(String path) throws FileNotFoundException, IOException{
        BufferedReader buffer = new BufferedReader(new FileReader(path));
            
        String line;
        Scanner s;

        String delimiter = "-";

        while((line = buffer.readLine()) != null){
            s = new Scanner(line);
            s.useDelimiter(delimiter);

            Area a = new Area(s.next(), s.next().substring(0).trim());
            this.addAreas(a);
            s.close();
        }
            for(Area k : this.areas){
                System.out.println(k.id + " - " + k.name);
            }

        buffer.close();
    }
    
    public void initTrainings(String path, int rk) throws FileNotFoundException, IOException{
        File samples = new File(path);
        File[] targetFiles;
        BufferedReader buffer;
        
        String line;
        Scanner s;

        String delimiter = ",";

        for (File dir : samples.listFiles()) {
            if (dir.getName().equals("trainings.txt")){
                buffer = new BufferedReader(new FileReader(dir));
                
                while ((line = buffer.readLine()) != null) {
                    s = new Scanner(line);
                    s.useDelimiter(delimiter);
                    
                    Article art = new Article(s.next());
                    
                    while(s.hasNext()){
                        for (Area a : this.areas){
                            if (s.next().equals(a.id)) {
                                art.addArea(a);
                            }
                        }
                    }
                    this.addTrainingArticle(art);
                    s.close();
                }
//                for (Area k : this.areas) {
//                    System.out.println(k.id + " - " + k.name);
//                }
                buffer.close();
                
            }
            if (dir.getName().equals("trainings")) {
                if (dir.exists() && dir.isDirectory()) {
                    targetFiles = dir.listFiles();

                    for (Article art : this.trainingList) {
                        for (File f : targetFiles) {
                            if (f.getName().equals(art.name)) {
                                PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
                                for (Term t : preProc.getTerms()) {
                                    if (t.numof >= rk) {
                                        art.addTerm(t);
                                        this.addWord(t.termStr);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
    
    public void initTargets(String path, int rk) throws FileNotFoundException, IOException {
        File samples = new File(path);
        File[] targetFiles;
        BufferedReader buffer;

        for (File dir : samples.listFiles()) {
            if (dir.getName().equals("targets.txt")){
                buffer = new BufferedReader(new FileReader(dir));
            }
            if (dir.getName().equals("targets")) {
                if (dir.exists() && dir.isDirectory()) {
                    targetFiles = dir.listFiles();

                    for (File f : targetFiles) {
                        Article art = new Article(f.getName());
                        PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
                        for (Term t : preProc.getTerms()) {
                            if (t.numof >= rk) {
                                art.addTerm(t);
                                this.addWord(t.termStr);
                            }
                        }
                        this.addTargetArticle(art);
                    }

                }
            }
        }

    }
    
}
