package org.example.bookmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class BookRendimientoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTiempoDeRespuestaListado() throws Exception {
        long inicio = System.currentTimeMillis();

        mockMvc.perform(get("/books"))
                .andReturn();

        long fin = System.currentTimeMillis();
        long duracion = fin - inicio;

        System.out.println("Tiempo de respuesta: " + duracion + " ms");
        assertTrue(duracion < 2000, "El tiempo de respuesta excede los 2 segundos");
    }
}
