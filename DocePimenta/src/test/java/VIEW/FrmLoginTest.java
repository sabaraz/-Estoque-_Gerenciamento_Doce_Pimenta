package VIEW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrmLoginTest {
    @Test
    public void testCreate() {
        FrmLogin tela = new FrmLogin();
        assertNotNull(tela);
    }
}
