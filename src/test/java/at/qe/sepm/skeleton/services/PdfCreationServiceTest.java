package at.qe.sepm.skeleton.services;

import com.itextpdf.text.DocumentException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PdfCreationServiceTest {

    @Autowired
    PdfCreationService pdfCreationService;

    @Test
    @DirtiesContext
    public void pdfCreationSimpleText() {
        File file = new File("./src/main/webapp/content/Verschluesselung.pdf");
        file.delete();
        try {
            pdfCreationService.createPdf("Test Test Test");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        file = new File("./src/main/webapp/content/Verschluesselung.pdf");
        Assert.assertTrue(file.exists());

    }

    @Test
    @DirtiesContext
    public void pdfCreationhardText() {
        File file = new File("./src/main/webapp/content/Verschluesselung.pdf");
        file.delete();
        try {
            pdfCreationService.createPdf("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                    "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                    "TestTestTestTestTestTestTestTest" +
                    "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        file = new File("./src/main/webapp/content/Verschluesselung.pdf");
        Assert.assertTrue(file.exists());
    }


}