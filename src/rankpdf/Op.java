/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.neuroph.core.data.DataSet;
import preprocessingpdf.PreProcessingPDF;
import preprocessingpdf.Term;

/**
 *
 * @author arthur
 */
public enum Op {
    tr{
        @Override
        public void init(String samples, RankPDFManager manager, int rk) throws Exception {
//            BufferedReader buffer = new BufferedReader(new FileReader(inputFile));
//            
//            String line;
//            Scanner s;
//            
//            String delimiter = ",";
//            
//            String folder = inputFile.substring(rk, rk);
//            File dir = new File(folder);
//            File [] trainingFiles;
//            
//            while((line = buffer.readLine()) != null){
//                s = new Scanner(line);
//                s.useDelimiter(delimiter);
//                System.out.println(s.next());
////                Area a = new Area(s.next(), s.next().substring(0).trim());
////                manager.addAreas(a);
////                s.close();
//            }
//            
////            for(Area k : manager.areas){
////                System.out.println(k.id + " - " + k.name);
////            }
//
//            buffer.close();
////            
////            if (dir.exists() && dir.isDirectory()) {
////                trainingFiles = dir.listFiles();
////                
////                for (File f : trainingFiles) {
////                    Article art = new Article(f.getName());
////                    PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
////                    for (Term t : preProc.getTerms()){
////                        if (t.numof >= rk) {
////                            art.addTerm(t);
////                            manager.addWord(t.termStr);   
////                        }
////                    }
////                    manager.addTrainingArticle(art);
////                }
////                
////            } else {
////                throw new Exception ("Parâmetro -tr errado.");
////            }
//            
        }
    }, tg{
        @Override
        public void init(String samples, RankPDFManager manager, int rk) throws Exception {
//            File dir = new File(inputFile);
//            File [] targetFiles;
//            
//            if (dir.exists() && dir.isDirectory()) {
//                targetFiles = dir.listFiles();
//                
//                for (File f : targetFiles) {
//                    Article art = new Article(f.getName());
//                    PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
//                    for (Term t : preProc.getTerms()){
//                        if (t.numof >= rk){
//                            art.addTerm(t);
//                            manager.addWord(t.termStr);
//                        }
//                    }
//                    manager.addTargetArticle(art);
//                }
//                
//            } else {
//                throw new Exception ("Parâmetro -tr errado.");
//            }
//        }
    }, cl{
        @Override
        public void init(String samples, RankPDFManager manager, int rank) throws Exception {
//            BufferedReader buffer = new BufferedReader(new FileReader(inputFile));
//            
//            String line;
//            Scanner s;
//            
//            String delimiter1 = ".pdf";
//            String delimiter2 = ",";
//            
//            while((line = buffer.readLine()) != null){
//                s = new Scanner(line);
//                s.useDelimiter(delimiter1);
//                
//                Area a = new Area(s.next(), s.next().substring(0).trim());
//                manager.addAreas(a);
//                s.close();
//            }
//            
////            for(Area k : manager.areas){
////                System.out.println(k.id + " - " + k.name);
////            }
//
//            buffer.close();
//        }
        
    };
    
    public abstract void init(String inputFile, RankPDFManager manager, int rank) throws Exception;
}
