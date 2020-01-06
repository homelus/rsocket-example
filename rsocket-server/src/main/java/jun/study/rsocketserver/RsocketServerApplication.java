package jun.study.rsocketserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Instant;

@SpringBootApplication
public class RsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsocketServerApplication.class, args);
    }

}

@Component
@AllArgsConstructor
class DataLoader {
    private final DealRepository repo;

    @PostConstruct
    void loadData() {
        repo.deleteAll().thenMany(Flux.just("pencil, pc, bag, book, paper, snack"))
                .map(Deal::new)
                .flatMap(repo::save)
                .subscribe(System.out::println);
    }

}

interface DealRepository extends ReactiveCrudRepository<Deal, String> {
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class DealOrder {
    private String dealId;
    private Instant whenOrdered;
}

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Document
class Deal {
    @Id
    private String id;
    @NonNull
    private String name;
}
