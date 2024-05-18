package br.com.boilerplate.java21.infrastructure.client.http;

import feign.Feign;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExampleClientFactory {

    @Value("${app.client.url}")
    private String clientUrl;

    public ExampleClient createClient() {
        return Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new GsonDecoder())
                .target(ExampleClient.class, clientUrl);
    }

}
