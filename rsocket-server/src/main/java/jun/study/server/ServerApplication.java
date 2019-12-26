package jun.study.server;

import io.rsocket.AbstractRSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author playjun
 * @since 2019 12 24
 */
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    public CommandLineRunner runner() {
        return args -> {
            Objects.requireNonNull(RSocketFactory.receive()
                    .frameDecoder(PayloadDecoder.DEFAULT.ZERO_COPY)
                    .acceptor((payload, connector) -> Mono.just(new AbstractRSocket() {
                    }))
                    .transport(TcpServerTransport.create(7878))
                    .start()
                    .doOnSuccess(System.out::println)
                    .block())
                    .onClose();
        };
    }
}
