package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository repo;
    public BeerLoader(BeerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {

        if(repo.count()==0) {
            repo.save(Beer.builder()
                    .beerName("Mangled Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .upc(12409890L)
                    .minOnHand(12)
                    .price(new BigDecimal("12.95"))
                    .build());
            repo.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .upc(12419033L)
                    .minOnHand(12)
                    .price(new BigDecimal("10.95"))
                    .build());
        }
        System.out.println("Loaded " + repo.count() + " beers.");
    }
}
