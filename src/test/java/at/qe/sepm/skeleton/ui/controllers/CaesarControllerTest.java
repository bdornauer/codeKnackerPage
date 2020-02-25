package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.ui.beans.InputHandleBean;
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
public class CaesarControllerTest {

    @Autowired
    private InputHandleBean inputHandleBean;

    @Autowired
    private CaesarController caesarController;

    private String encrytpedText1="defghijklmnopqrstuvwxyzabc".toUpperCase();

    @Before
    public void setUp() {
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
    }

    @Test
    public void handleEventWithWhitespace() {
        inputHandleBean.setInputText("abcdef      ghijklmn                opqrstuvwx           yz");
        inputHandleBean.setWithSpaces(true);
        caesarController.handleEvent(inputHandleBean);

        Assert.assertEquals(encrytpedText1, inputHandleBean.getCryptedText());
    }


    @Test
    public void handleEventWithSpecialCharacters() {
        inputHandleBean.setInputText("ab<><>€c.de,.fgh-ijkl*+#mn§§(/opq\"rstuvwx!%&$yz");
        inputHandleBean.setSpecialCharacters(true);
        caesarController.handleEvent(inputHandleBean);

        Assert.assertEquals(encrytpedText1, inputHandleBean.getCryptedText());
    }


    @Test
    public void handleEventParameters() {
        inputHandleBean.setInputText("   ab<><     >€c.de,.              fgh-ijkl*+#mn        §§(/opq\"rst      uvwx!%&$yz    ");
        inputHandleBean.setSpecialCharacters(true);
        inputHandleBean.setWithSpaces(true);
        caesarController.handleEvent(inputHandleBean);

        Assert.assertEquals(encrytpedText1, inputHandleBean.getCryptedText());
    }


    @Test
    public void handleEventEncrypt() {
        inputHandleBean.setInputText("   ab<><     >€c.de,.              fgh-ijkl*+#mn        §§(/opq\"rst      uvwx!%&$yz    ");
        inputHandleBean.setSpecialCharacters(true);
        inputHandleBean.setWithSpaces(true);
        caesarController.handleEvent(inputHandleBean);

        Assert.assertEquals(encrytpedText1, inputHandleBean.getCryptedText());
    }

    @Test
    public void handleEventDecrypt() {
        inputHandleBean.setInputText("def43ghij4$klmnop22---2371#++#+qrstuvwxyzabc87872312");
        inputHandleBean.setSpecialCharacters(true);
        inputHandleBean.setWithSpaces(true);
        try { //null pointer execption because javascript is executed but did not exists with junit test
            inputHandleBean.setDirection(true);
        }catch(NullPointerException e ){
            e.printStackTrace();
        }
        caesarController.handleEvent(inputHandleBean);

        Assert.assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", inputHandleBean.getCryptedText());

    }
}