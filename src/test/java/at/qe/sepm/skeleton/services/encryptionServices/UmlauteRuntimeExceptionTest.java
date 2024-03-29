package at.qe.sepm.skeleton.services.encryptionServices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UmlauteRuntimeExceptionTest {

    @Test(expected = UmlauteRuntimeException.class)
    public void getErrormessage() {
        throw new UmlauteRuntimeException();
    }
}