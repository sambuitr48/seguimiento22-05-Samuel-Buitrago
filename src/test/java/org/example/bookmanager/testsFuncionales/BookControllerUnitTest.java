package org.example.bookmanager;

import org.example.bookmanager.controller.BookController;
import org.example.bookmanager.model.Book;
import org.example.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class BookControllerUnitTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    public BookControllerUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveBook() {
        Book libro = new Book();
        libro.setTitle("El Principito");
        libro.setAuthor("Saint-Exupéry");
        libro.setGenre("Ficción");
        libro.setYear(1943);

        bookController.saveBook(libro);

        verify(bookRepository, times(1)).save(libro); // Verifica que se llama al método
    }
}
