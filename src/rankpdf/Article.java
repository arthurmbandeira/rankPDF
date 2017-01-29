/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import preprocessingpdf.Term;

/**
 *
 * @author arthur
 */
public class Article {

    String name;
    Map<String, Term> localDict;
    List<Area> areas;
    
    public Article(String name) {
        this.name = name;
        localDict = new HashMap(); 
        areas = new ArrayList(); 
    }
    
    public void addTerm(Term t){
        localDict.put(t.termStr, t);
    }
    
    public void getArticleSize(){
        localDict.size();
    }
    
    public void printArticle(){
        for (String k : localDict.keySet()){
            System.out.println(localDict.get(k).termStr);
        }
    }
    
    public void addArea(Area a){
        areas.add(a);
    }
    
    public void printAreas(){
        for (Area a : this.areas){
            System.out.println(a.id + a.name);
        }
    }
    
}
