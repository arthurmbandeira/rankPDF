package rankpdfT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import org.junit.Test;
import rankpdf.Op;
import rankpdf.RankPDF;
import rankpdf.RankPDFManager;
import static org.junit.Assert.assertEquals;
import rankpdf.Article;


/**
 *
 * @author arthur
 */
public class RankPDFTest {
    
    public RankPDFTest() {
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
     @Test
     public void inputs() throws IOException, Exception{
         String [] args = {"2", "./samples/areas.txt", "./samples/"};
         
         RankPDF.main(args);
    }
     
//    @Test
//    public void testTrainingFiles() throws Exception {
////        RankPDFManager manager = new RankPDFManager();
////        Op.tr.init("./samples/trainings.txt", manager, 2);
//        
////        manager.getBelongs().forEach(word -> System.out.println(word));
////        System.out.println("Size Trainings " + manager.belongs.size());
//        
////        Op.tg.init("./samples/targets", manager, 2);
//        
////        Op.ar.init("./samples/input_files/areas.txt", manager, 0);
//        
////        System.out.println("Size Trainings + Targets " + manager.belongs.size());
//        
////        Article artigo = manager.trainingList.get(0);
////        artigo.printArticle();
////        System.out.println("Target List Size " + manager.getTargetList().size());
//        
////        assertEquals("oi", "oi");
//    }
}
