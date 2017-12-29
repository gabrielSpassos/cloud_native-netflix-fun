package com.gabrielspassos.poc;

public class Application {
    public static void main(String[] args) {
        String valor1 = new CommandHelloWorld("Gabriel",0).execute();
        System.out.println(valor1);

        String valor2 = new CommandHelloWorld("Marcelo",2).execute();
        System.out.println(valor2);

        String valor3 = new CommandHelloWorld("Nome não importante",7).execute();
        System.out.println(valor3);
    }
}
