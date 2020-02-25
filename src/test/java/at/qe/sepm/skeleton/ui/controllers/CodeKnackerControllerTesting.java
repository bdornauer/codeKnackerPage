package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.ui.beans.CodeKnackerBeanTEST;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CodeKnackerControllerTesting {

    @Autowired
    private CodeKnackerControllerTEST codeKnackerControllerTEST;

    @Autowired
    private CodeKnackerBeanTEST codeKnackerBeanTEST;



    /*Test with SIMPLE and Caesar */

    @Test
    public void TestControllerCaeserAndSimple(){
        String plainText ="abc defg hijklmnopqrstuvwxyz!!!".toUpperCase();
        String encrytpedCaesar ="def ghij klmnopqrstuvwxyzabc!!!".toUpperCase();

        codeKnackerBeanTEST.setTechnique("caesar");
        codeKnackerBeanTEST.setInputText(encrytpedCaesar);
        codeKnackerBeanTEST.setDifficulty("Leicht");
        codeKnackerBeanTEST.setKeyCaesar(3);
        codeKnackerControllerTEST.updateCiphretextAlphabet(codeKnackerBeanTEST);

        Assert.assertEquals("Leicht", codeKnackerBeanTEST.getDifficulty());
        Assert.assertEquals("caesar", codeKnackerBeanTEST.getTechnique());
        Assert.assertEquals(plainText, codeKnackerBeanTEST.getOutputText());
    }

    @Test
    public void TestControllerCaeserAndHARD(){
        String plainText ="abcdefghijklmnopqrstuvwxyz".toUpperCase();
        String encrytpedCaesar ="defghi jklmnop qrst uvwxyzabc".toUpperCase();

        codeKnackerBeanTEST.setTechnique("caesar");
        codeKnackerBeanTEST.setInputText(encrytpedCaesar);
        codeKnackerBeanTEST.setDifficulty("Schwer");
        codeKnackerBeanTEST.setKeyCaesar(3);
        codeKnackerControllerTEST.updateCiphretextAlphabet(codeKnackerBeanTEST);

        Assert.assertEquals("Schwer", codeKnackerBeanTEST.getDifficulty());
        Assert.assertEquals("caesar", codeKnackerBeanTEST.getTechnique());
        Assert.assertEquals(plainText, codeKnackerBeanTEST.getOutputText());
    }


    @Test
    public void TestControllerAtbasch(){
        String plainText = "abc defg hijklmnopqrstuvwxyz!!!".toUpperCase();
        String encrytpedAtbasch ="ZYX WVUT SRQPONMLKJIHGFEDCBA!!!".toUpperCase();

        codeKnackerBeanTEST.setTechnique("atbasch");
        codeKnackerBeanTEST.setInputText(encrytpedAtbasch);
        codeKnackerBeanTEST.setDifficulty("SIMPLE");
        codeKnackerControllerTEST.updateCiphretextAlphabet(codeKnackerBeanTEST);

        Assert.assertEquals("SIMPLE", codeKnackerBeanTEST.getDifficulty());
        Assert.assertEquals("atbasch", codeKnackerBeanTEST.getTechnique());
        Assert.assertEquals(plainText, codeKnackerBeanTEST.getOutputText());
    }

    @Test
    public void TestSubstitutionAndReset(){
        String plainText = "abc defg hijklmnopqrstuvwxyz!!!".toUpperCase();
        String encrytpedAtbasch ="ZYX WVUT SRQPONMLKJIHGFEDCBA!!!".toUpperCase();

        String[] ciphretextAlphabet = new String[26];
        String ciphreAlphabet =  "ZYXWVUTSRQPONMLKJIHGFEDCBA".toUpperCase();

        codeKnackerBeanTEST.setTechnique("substitution");
        codeKnackerBeanTEST.setInputText(encrytpedAtbasch);
        codeKnackerBeanTEST.setDifficulty("SIMPLE");
        for (int i = 0; i < ciphreAlphabet.length(); i++) {
            ciphretextAlphabet[i] = Character.toString(ciphreAlphabet.charAt(i));
        }
        codeKnackerBeanTEST.setCiphretextAlphabet(ciphretextAlphabet);
        codeKnackerControllerTEST.updateCiphretextAlphabet(codeKnackerBeanTEST);
        codeKnackerControllerTEST.updateUseAndUnusedLetters(codeKnackerBeanTEST);

        //Test encryption
        Assert.assertEquals("SIMPLE", codeKnackerBeanTEST.getDifficulty());
        Assert.assertEquals("substitution", codeKnackerBeanTEST.getTechnique());
        Assert.assertEquals(plainText, codeKnackerBeanTEST.getOutputText());

        //Show unused and used letters
        Assert.assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", codeKnackerBeanTEST.getUsedLetters());
        Assert.assertEquals("--------------------------", codeKnackerBeanTEST.getUnusedLetters());


        //Test reset
        codeKnackerControllerTEST.resetAlphabet(codeKnackerBeanTEST);
        Assert.assertEquals("--------------------------", codeKnackerBeanTEST.getUsedLetters());
        Assert.assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", codeKnackerBeanTEST.getUnusedLetters());
    }

}
