package com.matheus.bookservice.controller;


import com.matheus.bookservice.repository.BookRepository;
import com.matheus.bookservice.entities.Book;
import com.matheus.bookservice.proxy.CambioProxy;
import com.matheus.bookservice.response.Cambio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
@Tag(name = "book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    Environment environment;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CambioProxy proxy;
    @Operation(summary = "find a book by id")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ){
        var book = bookRepository.getReferenceById(id);
        if(book == null){
            throw new RuntimeException("Book not found");
        }
        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());
        return book;
    }
//
//     @GetMapping(value = "/{id}/{currency}")
//     public Book findBook(
//     @PathVariable("id") Long id,
//     @PathVariable("currency") String currency
//     ) {
//
//     var book = bookRepository.getReferenceById(id);
//     if (book == null) throw new RuntimeException("Book not Found");
//
//     HashMap<String, String> params = new HashMap<>();
//     params.put("amount", book.getPrice().toString());
//     params.put("from", "USD");
//     params.put("to", currency);
//
//     var response = new RestTemplate()
//     .getForEntity("http://localhost:8000/cambio-service/"
//     + "{amount}/{from}/{to}",
//     Cambio.class,
//     params);
//
//     var cambio = response.getBody();
//
//     var port = environment.getProperty("local.server.port");
//     book.setEnvironment(port);
//     book.setPrice(cambio.getConvertedValue());
//     return book;
//     }
}
