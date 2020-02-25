package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.ui.beans.InputHandleBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PdfControllerTest {

    @Autowired
    private InputHandleBean inputHandleBean;

    @Autowired
    private PdfController pdfController;
    @Test
    public void createPDF() {
        File file = new File("./src/main/webapp/content/Verschluesselung.pdf");
        file.delete();

        inputHandleBean.setKey(3);
        inputHandleBean.setWithSpaces(false);
        inputHandleBean.setCryptedText("");
        try { //null pointer execption because javascript is executed but did not exists with junit test
            inputHandleBean.setDirection(false);
        }catch(NullPointerException e ){
            e.printStackTrace();
        }
        inputHandleBean.setSpecialCharacters(false);
        inputHandleBean.setCiphertextAlphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        inputHandleBean.setCryptedText("ZYXWVUTSRQPONMLKJIHGFEDCBA");

        pdfController.createPDF(inputHandleBean);

        file = new File("./src/main/webapp/content/Verschluesselung.pdf");
        Assert.assertTrue(file.exists());
    }
}