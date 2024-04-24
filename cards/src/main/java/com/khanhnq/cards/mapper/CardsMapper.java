package com.khanhnq.cards.mapper;

import com.khanhnq.cards.dto.CardsDto;
import com.khanhnq.cards.entity.Cards;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardsMapper {
    CardsMapper CARDS_MAPPER = Mappers.getMapper(CardsMapper.class);
    CardsDto mapToCardsDto (Cards cards);
    void updateCards (@MappingTarget Cards cards, CardsDto cardsDto);
}
