package VIEW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrmVendaTest {
    @Test
    public void testCreate() {
        FrmVenda tela = new FrmVenda();
        assertNotNull(tela);
    }
}
