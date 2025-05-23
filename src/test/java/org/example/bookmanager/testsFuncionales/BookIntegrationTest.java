package org.example.bookmanager.testsFuncionales;

import org.example.bookmanager.model.Book;
import org.example.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testAgregarLibroFlujoCompleto() throws Exception {
        mockMvc.perform(post("/books")
                        .param("title", "Frankestein")
                        .param("author", "Samuel Buitrago Osorio")
                        .param("genre", "Romance")
                        .param("year", "700"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        Book libro = bookRepository.findAll().stream()
                .filter(b -> b.getTitle().equals("Frankestein"))
                .findFirst()
                .orElseThrow();

        assertEquals("Samuel Buitrago Osorio", libro.getAuthor());
    }
}
