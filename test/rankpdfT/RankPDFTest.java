package rankpdfT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import org.junit.Test;
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
         String [] args = {"2", "./samples/areas.txt", 
                                "./samples/trainings", 
                                "./samples/trainings.txt", 
                                "./samples/targets", 
                                "./samples/targets.txt"
         };
         
         RankPDF.main(args);
    }
}
