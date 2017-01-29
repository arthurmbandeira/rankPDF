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

    public void initTrainings(String pathFolder, String pathFile, int rk) throws FileNotFoundException, IOException {
        File trainingFolder = new File(pathFolder);
        File config = new File(pathFile);
        File[] trainingFiles;
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

            while (s.hasNext()) {
                String aux = s.next().trim();
                for (Area a : this.areas) {
                    if (aux.equals(a.id)) {
                        art.addArea(a);
                    }
                }
            }
            this.addTrainingArticle(art);
            s.close();
        }
        buffer.close();
        if (trainingFolder.exists() && trainingFolder.isDirectory()) {
            trainingFiles = trainingFolder.listFiles();
            for (Article ar : this.trainingList) {
                for (File f : trainingFiles) {
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

            while (s.hasNext()) {
                String aux = s.next().trim();
                for (Area a : this.areas) {
                    if (aux.equals(a.id)) {
                        art.addArea(a);
                    }
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

}
