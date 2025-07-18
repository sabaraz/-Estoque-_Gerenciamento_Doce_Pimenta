package VIEW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrmEntregadorTest {
    @Test
    public void testCreate() {
        FrmEntregador tela = new FrmEntregador();
        assertNotNull(tela);
    }
}
