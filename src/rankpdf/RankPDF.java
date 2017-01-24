/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rankpdf;

import java.io.IOException;
import preprocessingpdf.PreProcessingPDF;

/**
 *
 * @author arthur
 */
public class RankPDF {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        PreProcessingPDF preProc = new PreProcessingPDF("../tests/PDFs/102.pdf");
        
//        preProc.utils.printTerms(preProc);
        preProc.utils.printTermsInFile(preProc);
//        preProc.utils.writePDFInfo(preProc);
    }
    
}
