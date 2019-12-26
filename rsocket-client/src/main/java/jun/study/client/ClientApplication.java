package jun.study.client;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

/**
 * @author playjun
 * @since 2019 12 26
 */
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    public CommandLineRunner runner() {
        return args -> {
            Mono<RSocket> client = RSocketFactory.connect()
                    .frameDecoder(PayloadDecoder.DEFAULT)
                    .transport(TcpClientTransport.create(7878))
                    .start();


        };
    }

}
