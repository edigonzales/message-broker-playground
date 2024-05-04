//REPOS mavencentral,ehi=https://jars.interlis.ch/
//DEPS ch.interlis:ilivalidator:1.14.1
//DEPS ch.ehi:ehibasics:1.4.1
//DEPS org.apache.camel:camel-bom:4.5.0@pom
//DEPS org.apache.camel:camel-main
//DEPS org.apache.camel:camel-activemq
//DEPS org.apache.camel:camel-debug
//DEPS org.apache.camel:camel-file
//DEPS org.apache.camel:camel-health
//DEPS org.apache.camel:camel-jms

import ch.ehi.basics.settings.Settings;
import org.interlis2.validator.Validator;

import org.apache.camel.main.Main;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;

import static org.apache.activemq.ActiveMQConnection.DEFAULT_BROKER_URL;

import java.nio.file.Paths;
import java.util.UUID;

public class consume_messages extends RouteBuilder {
    private static final String TMP_DIR = "/Users/stefan/tmp/";

    Main main = new Main();
    
    @Override
    public void configure() throws Exception {
        // Kann man sich sparen, falls Default-Url verwendet wird.
        main.bind("activemq", ActiveMQComponent.activeMQComponent(DEFAULT_BROKER_URL));
        main.bind("activemqConnectionFactory", ActiveMQConnectionFactory.class);

        from("activemq:queue:ch/so/dsbjd/ebau" +
            "?username=user" +
            "&password=1234")
        .setHeader("CamelFileName", method(consume_messages.class, "generateFileName"))
        .to("file:"+TMP_DIR)
        .choice()
            .when(new Predicate() {
                @Override
                public boolean matches(Exchange exchange) {
                    Settings settings = new Settings();
                    settings.setValue(Validator.SETTING_ILIDIRS, ".;"+Validator.SETTING_DEFAULT_ILIDIRS);
                    String fileName = (String) exchange.getIn().getHeader("CamelFileName");
                    boolean valid = Validator.runValidation(Paths.get(TMP_DIR, fileName).toString(), settings);
                    return valid;
                }
            }).process(exchange -> {
                System.out.println("File is valid and will be imported: " + exchange.getIn().getHeader("CamelFileName"));
                // ili2pg...
            })
            .otherwise().log("File is NOT valid.")
        .end();
        //.log("Received a message - ${body}");
    }

    public static String generateFileName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + ".xtf";
    }
}