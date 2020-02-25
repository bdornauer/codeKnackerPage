package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.ui.beans.EmailBean;
import at.qe.sepm.skeleton.ui.beans.InputHandleBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmailControllerTest {

    @Autowired
    EmailBean emailBean;

    @Autowired
    EmailController emailController;

    @Autowired
    private InputHandleBean inputHandleBean;

    @Test
    public void sendEmail() {
        emailBean.setMessage("TestTEst");
        emailBean.setName("Benedikt Dornauer");
        emailBean.setTo("benedikt.dornauer@hotmail.com");

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



        emailController.sendEmail(emailBean, inputHandleBean);
    }

    @Test(expected = MailSendException.class)
    public void sendWrongEmail() {
        emailBean.setMessage("TestTEst");
        emailBean.setName("Benedikt Dornauer");
        emailBean.setTo("b.dornauer");

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



        emailController.sendEmail(emailBean, inputHandleBean);
    }
}