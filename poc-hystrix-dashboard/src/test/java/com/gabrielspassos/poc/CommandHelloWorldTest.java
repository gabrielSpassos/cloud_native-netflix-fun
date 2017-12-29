package com.gabrielspassos.poc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandHelloWorldTest {

    @Test
    public void mustReturnMarcelo(){
        Message messageExpected = new Message();
        messageExpected.setText("Hello Marcelo! Thread waited for 0 seconds");

        Message messageReturned = new CommandHelloWorld("Marcelo",0).execute();

        assertEquals(messageExpected.getText(),messageReturned.getText());
    }

    @Test
    public void mustReturnFallback(){
        Message messageExpected = new Message();
        messageExpected.setText("Hello World! Thread waited for 6 seconds. This is a fallback message");

        Message messageReturned = new CommandHelloWorld("Nome não deve ser usado",6).execute();

        assertEquals(messageExpected.getText(),messageReturned.getText());

    }
}
