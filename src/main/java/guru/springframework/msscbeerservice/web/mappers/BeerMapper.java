package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.web.model.BeerDto;

public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer)
}
