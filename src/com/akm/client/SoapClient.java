package com.akm.client;

import com.akm.client.model.Book;
import com.akm.client.parser.XmlParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SoapClient {

    private final String endpoint;

    private final HttpClient httpClient =
            HttpClient.newHttpClient();

    private final XmlParser parser = new XmlParser();

    public SoapClient(String server) {
        this.endpoint = "http://" + server + "/ws";
    }

    public void getBook(Long id) {

        String soapRequest = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                        xmlns:bk="http://akm.com/books">

                    <soapenv:Header/>

                    <soapenv:Body>
                        <bk:getBookRequest>
                            <bk:id>%d</bk:id>
                        </bk:getBookRequest>
                    </soapenv:Body>

                </soapenv:Envelope>
                """.formatted(id);

        String response = send(soapRequest);

        Book book = parser.parseBook(response);

        if (book != null) {
            Menu.printBook(book);
        }
    }

    public void getAllBooks() {

        String soapRequest = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                        xmlns:bk="http://akm.com/books">

                    <soapenv:Header/>

                    <soapenv:Body>
                        <bk:getAllBooksRequest/>
                    </soapenv:Body>

                </soapenv:Envelope>
                """;

        String response = send(soapRequest);
//        System.out.println(response);

        List<Book> books = parser.parseBooks(response);

        Menu.printBooks(books);
    }

    public void createBook(Book book) {

        String soapRequest = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                        xmlns:bk="http://akm.com/books">

                    <soapenv:Header/>

                    <soapenv:Body>
                        <bk:createBookRequest>
                            <bk:book>
                                <bk:id>%d</bk:id>
                                <bk:isbn>%s</bk:isbn>
                                <bk:title>%s</bk:title>
                                <bk:author>%s</bk:author>
                                <bk:publicationYear>%d</bk:publicationYear>
                            </bk:book>
                        </bk:createBookRequest>
                    </soapenv:Body>

                </soapenv:Envelope>
                """.formatted(
                book.id(),
                book.isbn(),
                book.title(),
                book.author(),
                book.publicationYear()
        );

        String response = send(soapRequest);

        Book createdBook = parser.parseBook(response);

        System.out.println();
        System.out.println("BOOK CREATED");
        Menu.printBook(createdBook);
    }

    public void updateBook(Book book) {

        String soapRequest = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                        xmlns:bk="http://akm.com/books">

                    <soapenv:Header/>

                    <soapenv:Body>
                        <bk:updateBookRequest>
                            <bk:book>
                                <bk:id>%d</bk:id>
                                <bk:isbn>%s</bk:isbn>
                                <bk:title>%s</bk:title>
                                <bk:author>%s</bk:author>
                                <bk:publicationYear>%d</bk:publicationYear>
                            </bk:book>
                        </bk:updateBookRequest>
                    </soapenv:Body>

                </soapenv:Envelope>
                """.formatted(
                book.id(),
                book.isbn(),
                book.title(),
                book.author(),
                book.publicationYear()
        );

        String response = send(soapRequest);
        Book updatedBook = parser.parseBook(response);

        System.out.println();
        System.out.println("BOOK UPDATED");
        Menu.printBook(updatedBook);
    }

    public void deleteBook(Long id) {

        String soapRequest = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                        xmlns:bk="http://akm.com/books">

                    <soapenv:Header/>

                    <soapenv:Body>
                        <bk:deleteBookRequest>
                            <bk:id>%d</bk:id>
                        </bk:deleteBookRequest>
                    </soapenv:Body>

                </soapenv:Envelope>
                """.formatted(id);

        String response = send(soapRequest);

        boolean success = parser.parseSuccess(response);

        System.out.println();

        if (success) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Delete operation failed.");
        }
    }

    private String send(String xml) {

        try {

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(endpoint))
                            .header("Content-Type", "text/xml;charset=UTF-8")
                            .POST(HttpRequest.BodyPublishers.ofString(xml))
                            .build();

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}