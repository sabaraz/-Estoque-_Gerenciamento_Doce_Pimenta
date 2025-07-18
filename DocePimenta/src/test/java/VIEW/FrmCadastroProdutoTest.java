package VIEW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrmCadastroProdutoTest {
    @Test
    public void testCreate() {
        FrmCadastroProduto tela = new FrmCadastroProduto();
        assertNotNull(tela);
    }
}
