package org.example.was;

import java.io.IOException;

//GET /calcultae?operand1=11&operator=*&operand2=55
public class Main {
    public static void main(String[] args) throws IOException {

        new CustomWebApplicationServer(8080).start();
    }
}