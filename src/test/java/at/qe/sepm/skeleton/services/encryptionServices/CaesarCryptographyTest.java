package at.qe.sepm.skeleton.services.encryptionServices;

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
public class CaesarCryptographyTest {

    @Autowired
    private CaesarCryptography caesarCryptography;

    private int key1 = 3;
    private int key2 = 30;

    String plainText = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    String encrytpedText1="defghijklmnopqrstuvwxyzabc".toUpperCase();
    String encrytpedText2 ="efghijklmnopqrstuvwxyzabcd".toUpperCase();

    @Test
    public void encryptString() {
        Assert.assertEquals( encrytpedText1, caesarCryptography.encryptString(plainText,3) );
        Assert.assertEquals( encrytpedText2, caesarCryptography.encryptString(plainText,30) );
    }


    @Test
    public void decryptString() {
        Assert.assertEquals( plainText, caesarCryptography.decryptString(encrytpedText1,3) );
        Assert.assertEquals( plainText, caesarCryptography.decryptString(encrytpedText2,30) );
    }

    @Test
    public void encrypt() {
        //with two cases
        caesarCryptography.setKey(3);
        Assert.assertEquals(4, caesarCryptography.encrypt(1));
        caesarCryptography.setKey(30);
        Assert.assertEquals(5, caesarCryptography.encrypt(1));
    }

    @Test
    public void decrypt() {
        caesarCryptography.setKey(3);
        Assert.assertEquals(1, caesarCryptography.decrypt(4));
        caesarCryptography.setKey(30);
        Assert.assertEquals(1, caesarCryptography.decrypt(5));

    }

    @Test
    public void getAndSetKey() {
        caesarCryptography.setKey(3);
        Assert.assertEquals(3, caesarCryptography.getKey());
    }

}