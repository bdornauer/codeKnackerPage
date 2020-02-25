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
public class AtbaschCryptographyTest {
    @Autowired
    AtbaschCryptography atbaschCryptography;

    String plainText =   "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String encrytpedText="ZYXWVUTSRQPONMLKJIHGFEDCBA";

    @Test
    public void encryptString() {
        Assert.assertEquals(encrytpedText, atbaschCryptography.encryptString(plainText));
    }

    @Test
    public void decryptString() {
        Assert.assertEquals(plainText, atbaschCryptography.decryptString(encrytpedText));
    }

    @Test
    public void encrypt() {
        for(int i=0; i < 26; i++){
            Assert.assertEquals(25-i, atbaschCryptography.encrypt(i));
        }
    }

    @Test
    public void decrypt() {
        for(int i=0; i < 26; i++){
            Assert.assertEquals(25-i, atbaschCryptography.decrypt(i));
        }
    }
}