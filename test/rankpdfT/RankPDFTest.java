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
     public void inputs() throws IOException, Exception {
         String [] args = {"2", "-tr", "./samples/trainings", "-tg", "./samples/targets"};
         
         RankPDF.main(args);
    }
     
    @Test
    public void testTrainingFiles() throws Exception {
        RankPDFManager manager = new RankPDFManager();
        Op.tr.init("./samples/trainings", manager, 2);
        
        manager.getBelongs().forEach(word -> System.out.println(word));
        
        assertEquals("oi", "oi");
    }
}
