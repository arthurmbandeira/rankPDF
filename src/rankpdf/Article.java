/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import preprocessingpdf.Term;

/**
 *
 * @author arthur
 */
public class Article {

    String name;
    Map<String, Term> localDict;
    
    public Article(String name) {
        localDict = new HashMap(); 
    }
    
    public void addTerm(Term t){
        localDict.put(t.termStr, t);
    }
    
}
