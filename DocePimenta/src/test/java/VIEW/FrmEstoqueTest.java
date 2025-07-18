package VIEW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrmEstoqueTest {
    @Test
    public void testCreate() {
        FrmEstoque tela = new FrmEstoque();
        assertNotNull(tela);
    }
}
