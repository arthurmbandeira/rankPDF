/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
            System.out.println(this.name + " - " + a.id);
        }
    }
    
    public ArrayList<Double> getOccurenceList(String[] columns){
        ArrayList<Double> occurrenceList = new ArrayList();
        int totalOccurrences = 0;
        for (Term t : localDict.values()) {
            totalOccurrences += t.numof;
        }
        
        NumberFormat formatter = new DecimalFormat("#0.00000");
        for (String w : columns){
            if (this.localDict.containsKey(w)) {
                double d = ((double) localDict.get(w).numof) / totalOccurrences;
                occurrenceList.add(d);
            } else {
                occurrenceList.add(0.0);
            }
        }
        return occurrenceList;
    }
    
    public ArrayList<Double> getAreaList(){
        ArrayList<Double> areaList = new ArrayList();
        for (Area a : areas) {
            if (a == null) {
                areaList.add(0.0);
            } else {
                areaList.add(1.0);
            }
        }
        
        return areaList;
    }
    
    public double [] getOccurenceVector(String[] columns){
        double [] doubleVector =  new double[columns.length];
        int totalOccurrences = 0;
        for (Term t : localDict.values()) {
            totalOccurrences += t.numof;
        }
        NumberFormat formatter = new DecimalFormat("#0.00000");
        int i = 0;
        for (String w : columns){
            if (this.localDict.containsKey(w)) {
                double d = ((double) localDict.get(w).numof) / totalOccurrences;
                doubleVector[i] = d;
            } else {
                doubleVector[i] = 0.0;
            }
            i++;
        }
        return doubleVector;
    }
    
}
