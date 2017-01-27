/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.io.File;
import preprocessingpdf.PreProcessingPDF;
import preprocessingpdf.Term;

/**
 *
 * @author arthur
 */
public enum Op {
    tr{
        @Override
        public void init(String input, RankPDFManager manager, int rk) throws Exception {
            File dir = new File(input);
            File [] trainingFiles;
            
            if (dir.exists() && dir.isDirectory()) {
                trainingFiles = dir.listFiles();
                
                for (File f : trainingFiles) {
                    Article art = new Article(f.getName());
                    PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
                    for (int i = 0; i < rk; i++) {
                        Term t = preProc.getTerms().get(i);
                        art.addTerm(t);
                        manager.addWord(t.termStr);
                    }
                    manager.addTrainingArticle(art);
                }
                
            } else {
                throw new Exception ("Parâmetro -tr errado.");
            }
            System.out.println("Voce escolheu Train " + input);
        }
    }, tg{
        @Override
        public void init(String input, RankPDFManager manager, int rk) throws Exception {
            File dir = new File(input);
            File [] targetFiles;
            
            if (dir.exists() && dir.isDirectory()) {
                targetFiles = dir.listFiles();
                
                for (File f : targetFiles) {
                    Article art = new Article(f.getName());
                    PreProcessingPDF preProc = new PreProcessingPDF(f.getAbsolutePath());
                    for (int i = 0; i < rk; i++) {
                        Term t = preProc.getTerms().get(i);
                        art.addTerm(t);
                        manager.addWord(t.termStr);
                    }
                    manager.addTargetArticle(art);
                }
                
            } else {
                throw new Exception ("Parâmetro -tr errado.");
            }
            System.out.println("Voce escolheu Target " + input);
        }
    };
    
    public abstract void init(String input, RankPDFManager manager, int ranking) throws Exception;
}
