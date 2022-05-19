package ato.dharma.example.unmarshallingdemo1.route;

import ato.dharma.example.unmarshallingdemo1.CurrentExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .contextPath("/").host("0.0.0.0").port(8081)
//                .port(8082)
                .bindingMode(RestBindingMode.auto);

//        from("kafka:kafkaMessage")
//                //  .to("kafka:output_topic?brokers=localhost:9092") //.to("log:kafka Message Received");
//                //  .log("${body}")
//                .filter(simple("${body.mode} == 'chat'"))           // filter for mode
//                .to("kafka:output_topic?brokers=localhost:9092") //.to("log:kafka Message Received");
//                .unmarshal().json(CurrentExchange.class)

        from("kafka:output_topic?brokers=localhost:9092")
                //.marshal().json(CurrentExchange.class)
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}")
                //.filter(simple("${body.mode} == 'chat'"))           // filter for mode
               // .log("${body}")
                .to("kafka:output_topic1?brokers=localhost:9092")

                .end();
    }
}
