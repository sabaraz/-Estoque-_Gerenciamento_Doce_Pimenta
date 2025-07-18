package VIEW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrmCadastroUsuarioTest {
    @Test
    public void testCreate() {
        FrmCadastroUsuario tela = new FrmCadastroUsuario();
        assertNotNull(tela);
    }
}
