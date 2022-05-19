package ato.dharma.example.unmarshallingdemo1;

import org.apache.camel.Exchange;
import org.springframework.web.bind.annotation.GetMapping;

public class MyController {
    @GetMapping("/")  // maps to the following method. When invoked from a browser or cURL on
    // command line, the method returns text
    public String index() {
        return "Hello Devs, Greetings from Dharma!";
    }



}
