package at.qe.sepm.skeleton.services;

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
public class TextConverterServiceTest {

    @Autowired
    TextConverterService textConverterService;

    private int asciiValuesAlphabetLarge[]= new int[26];
    private int asciiValuesAlphabetSmall[]= new int[26];
    private String alphabetLarge = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String alphabetSmall = "abcdefghijklmnopqrstuvwxyz";
    private char[] alphabetCharacterArraySmall = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char[] alphabetCharacterArrayLarge = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Before
    public void setUp(){
        for(int i=65; i <= 90; i++){
            asciiValuesAlphabetLarge[i-65] = i;
        }

        for(int i=97; i <= 122; i++){
            asciiValuesAlphabetSmall[i-97] = i;
        }
    }


    @Test
    public void checkIfLetterInAlphabet() {
        Assert.assertEquals(true, textConverterService.checkIfLetterInAlphabet('a'));
    }

    @Test
    public void checkIfUmlaut() {
        Assert.assertEquals(true, textConverterService.checkIfUmlaut('ä'));
        Assert.assertEquals(true, textConverterService.checkIfUmlaut('ö'));
        Assert.assertEquals(true, textConverterService.checkIfUmlaut('ü'));
        Assert.assertEquals(true, textConverterService.checkIfUmlaut('Ä'));
        Assert.assertEquals(true, textConverterService.checkIfUmlaut('Ö'));
        Assert.assertEquals(true, textConverterService.checkIfUmlaut('Ü'));
    }

    @Test
    public void stringToAsciiArray() {
        Assert.assertArrayEquals(asciiValuesAlphabetSmall, textConverterService.stringToAsciiArray(alphabetSmall));
        Assert.assertArrayEquals(asciiValuesAlphabetLarge, textConverterService.stringToAsciiArray(alphabetLarge));
    }

    @Test
    public void asciiArrayToString() {
        Assert.assertEquals(alphabetSmall,textConverterService.asciiArrayToString(asciiValuesAlphabetSmall));
        Assert.assertEquals(alphabetLarge, textConverterService.asciiArrayToString(asciiValuesAlphabetLarge));
    }

    @Test
    public void replaceUmlaute() {
        Assert.assertEquals("aeueoeAEUEOE",textConverterService.replaceUmlaute("äüöÄÜÖ"));
    }

    @Test
    public void integrateUmlaute() {
        Assert.assertEquals("äüöÄÜÖ", textConverterService.integrateUmlaute("aeueoeAEUEOE"));
    }

    @Test
    public void stringToCharArray() {
        Assert.assertArrayEquals(alphabetCharacterArraySmall, textConverterService.stringToCharArray(alphabetSmall));
        Assert.assertArrayEquals(alphabetCharacterArrayLarge, textConverterService.stringToCharArray(alphabetLarge));
    }

    @Test
    public void charArrayToString() {
        Assert.assertEquals(alphabetSmall, textConverterService.charArrayToString(alphabetCharacterArraySmall));
        Assert.assertEquals(alphabetLarge, textConverterService.charArrayToString(alphabetCharacterArrayLarge));
    }

    @Test
    public void stringToUpperCase() {
        Assert.assertEquals(alphabetLarge, textConverterService.stringToUpperCase(alphabetSmall));
    }

    @Test
    public void deleteSpecialCharacters() {
        Assert.assertEquals("abc",textConverterService.deleteSpecialCharacters("a!%$§%&//?()b=)((//(c"));
    }

    @Test
    public void removeWhitespacesAndTabs() {
        Assert.assertEquals("abc",textConverterService.removeWhitespacesAndTabs("a   b       c        "));
    }

    @Test
    public void firstCharToArray() {
        String[] stringArray ={"ABC", "dfe", "hij"};
        char[] firstLetter = {'A', 'd','h'};
        Assert.assertArrayEquals(firstLetter, textConverterService.firstCharToArray(stringArray));
    }

    @Test
    public void counterLettersInString() {
        Integer[] intArray = new Integer[26];
        intArray[0] = 2;
        intArray[25] = 2;

        Assert.assertEquals(intArray[0],textConverterService.counterLettersInString("aA   zZ")[0]);
        Assert.assertEquals(intArray[25],textConverterService.counterLettersInString("aA   zZ")[25]);
    }
}