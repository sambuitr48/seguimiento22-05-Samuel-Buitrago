package org.example.bookmanager.testsNoFuncionales;

import org.example.bookmanager.model.Book;
import org.example.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookCargaTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testCargaConcurrenteAlta() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 20; i++) {
                Book libro = new Book();
                libro.setTitle("Libro " + i);
                libro.setAuthor("Autor " + i);
                libro.setGenre("Ficción");
                libro.setYear(2000 + i);
                bookRepository.save(libro);
            }
        };

        Thread hilo1 = new Thread(task);
        Thread hilo2 = new Thread(task);
        Thread hilo3 = new Thread(task);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();

        System.out.println("Carga finalizada. Total libros en BD: " + bookRepository.count());
    }
}
