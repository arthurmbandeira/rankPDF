/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author arthur
 */
public class RankPDFManager {

    List<Article> trainingList;
    List<Article> testList;
    
    public Set<String> belongs;
    
    public RankPDFManager() {
        trainingList = new ArrayList();
        testList = new ArrayList();
        belongs = new HashSet();
    }
    
    public void addTrainingArticle(Article a) {
        trainingList.add(a);
    }
    
    public void addTestArticle(Article a) {
        testList.add(a);
    }
    
    public void addWord(String s){
        belongs.add(s);
    }
    
    
}
