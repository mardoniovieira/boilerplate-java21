package br.com.boilerplate.java21.infrastructure.client.http;

import br.com.boilerplate.java21.presentation.dto.record.MyIpRecord;
import feign.RequestLine;

public interface ExampleClient {

    @RequestLine("GET /ip")
    MyIpRecord searchMyIp();

}
