package at.qe.sepm.skeleton.services.encryptionServices;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SubstitutionCryptographyTest {

    @Autowired
    private SubstitutionCryptography substitutionCryptography;

    //for testing atbasch substitution alphabet
    private String substitionCiphreAlphabet = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
    private String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String encrytpedText = "ZYXWVUTSRQPONMLKJIHGFEDCBA"; //equall to substitionCiphreAlphabet

    @Before
    public void setUp()  {
        substitutionCryptography.setCiphertextAlphabet(substitionCiphreAlphabet);
    }

    @Test
    public void encryptString() {
        Assert.assertEquals(encrytpedText, substitutionCryptography.encryptString(plainText, substitionCiphreAlphabet));
    }

    @Test
    public void decryptString() {
        Assert.assertEquals(encrytpedText, substitutionCryptography.encryptString(substitionCiphreAlphabet, plainText));
    }

    @Test
    public void encrypt() {
        for(int i=0; i < 26; i++){
            Assert.assertEquals(25-i, substitutionCryptography.encrypt(i));
        }
    }

    @Test
    public void decrypt() {
        for(int i=0; i < 26; i++){
            Assert.assertEquals(25-i, substitutionCryptography.decrypt(i));
        }
    }

    @Test
    public void getAndSetCiphertextAlphabet() {
        substitutionCryptography.setCiphertextAlphabet("EFGHIJKLMNOPQRSTUVWXYZ");
        Assert.assertEquals("EFGHIJKLMNOPQRSTUVWXYZ", substitutionCryptography.getCiphertextAlphabet());
    }

}