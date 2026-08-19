# SOAP Book Client

A console-based SOAP client built in Java to consume the SOAP Book Server.

## Why I Built This

After building the SOAP Book Server, I wanted to understand the client side of SOAP-based communication as well. Instead of relying on frameworks or generated client stubs, I chose to manually construct SOAP requests and parse SOAP responses.

The goal was to gain a deeper understanding of how SOAP messages are exchanged over HTTP and how XML-based service contracts are consumed by client applications.

This project is the companion application to the SOAP Book Server and completes the end-to-end SOAP learning experience.

## Features

- Connect to a SOAP server using an IP address and port
- Create a book
- Retrieve a book by ID
- Retrieve all books
- Update book information
- Delete a book
- Parse SOAP XML responses into Java objects
- Console-based interactive menu

## Technology Stack

- Java 25
- Java HTTP Client (`java.net.http.HttpClient`)
- XML DOM Parsing
- SOAP
- XML

## Project Structure

```text
src/com/akm/client
├── Main.java
├── Menu.java
├── SoapClient.java
├── model
│   └── Book.java
└── parser
    └── XmlParser.java
```

## How It Works

The client:

1. Accepts the SOAP server address from the user.
2. Builds SOAP XML requests manually.
3. Sends requests using Java's `HttpClient`.
4. Receives SOAP XML responses.
5. Parses XML responses into Java objects.
6. Displays results through a console-based menu.

## Running the Application

### Compile

```bash
javac -d out $(find src -name "*.java")
```

### Run

```bash
java -cp out com.akm.client.Main
```

### Run Packaged JAR

```bash
java -jar soap-book-client.jar
```

## Example Usage

```text
SOAP Server (ip:port): localhost:8080

1. Get Book
2. Get All Books
3. Create Book
4. Update Book
5. Delete Book
0. Exit
```

## Learning Outcomes

Through this project I learned:

- How SOAP messages are structured and transmitted
- How SOAP communication works over HTTP
- Manual XML request generation
- XML response parsing in Java
- Consuming a contract-first SOAP service
- Building an end-to-end SOAP integration without client-side frameworks

## Related Project

This repository contains the client component of the project.

The companion server application exposes SOAP endpoints using Spring Web Services and an XSD-defined contract.

**Server Repository:**  
https://github.com/animesh0404/soap-book-server