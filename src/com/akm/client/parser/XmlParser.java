package com.akm.client.parser;

import com.akm.client.model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public Book parseBook(String xml) {

        try {

            Document doc = build(xml);

            Element bookElement =
                    (Element) doc.getElementsByTagNameNS(
                            "http://akm.com/books",
                            "book"
                    ).item(0);

            if (bookElement == null) {
                return null;
            }

            return parseBook(bookElement);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> parseBooks(String xml) {

        try {

            Document doc = build(xml);

            NodeList bookNodes =
                    doc.getElementsByTagNameNS(
                            "http://akm.com/books",
                            "book"
                    );
            System.out.println(
                    "Books found: "
                            + bookNodes.getLength()
            );
            List<Book> books = new ArrayList<>();

            for (int i = 0; i < bookNodes.getLength(); i++) {

                books.add(
                        parseBook((Element) bookNodes.item(i))
                );
            }

            return books;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean parseSuccess(String xml) {

        try {

            Document doc = build(xml);

            NodeList nodes =
                    doc.getElementsByTagNameNS(
                            "http://akm.com/books",
                            "success"
                    );

            if (nodes.getLength() == 0) {
                return false;
            }

            return Boolean.parseBoolean(
                    nodes.item(0).getTextContent()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Book parseBook(Element bookElement) {

        Long id =
                Long.parseLong(
                        getValue(bookElement, "id")
                );

        String isbn =
                getValue(bookElement, "isbn");

        String title =
                getValue(bookElement, "title");

        String author =
                getValue(bookElement, "author");

        Integer publicationYear =
                Integer.parseInt(
                        getValue(bookElement, "publicationYear")
                );

        return new Book(
                id,
                isbn,
                title,
                author,
                publicationYear
        );
    }

    private String getValue(
            Element parent,
            String tagName) {

        return parent
                .getElementsByTagNameNS(
                        "http://akm.com/books",
                        tagName
                )
                .item(0)
                .getTextContent();
    }

    private Document build(String xml)
            throws Exception {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);

        return factory
                .newDocumentBuilder()
                .parse(
                        new ByteArrayInputStream(
                                xml.getBytes(
                                        StandardCharsets.UTF_8
                                )
                        )
                );
    }
}