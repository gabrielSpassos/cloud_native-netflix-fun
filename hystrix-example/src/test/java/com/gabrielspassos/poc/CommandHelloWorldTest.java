package com.gabrielspassos.poc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandHelloWorldTest {

    @Test
    public void mustReturnMarcelo(){
        assertEquals("Hello Marcelo!\nThread waited for 0 seconds", new CommandHelloWorld("Marcelo",0).execute());
    }

    @Test
    public void mustReturnFallback(){
        assertEquals("\n***************************\nHello World! \nThread waited for 6 seconds"+
                "\nThis is a fallback message\n***************************\n",
                new CommandHelloWorld("Nome não deve ser usado",6).execute());
    }
}
