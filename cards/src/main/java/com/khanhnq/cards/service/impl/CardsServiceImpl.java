package com.khanhnq.cards.service.impl;

import com.khanhnq.cards.constants.CardsConstants;
import com.khanhnq.cards.dto.CardsDto;
import com.khanhnq.cards.entity.Cards;
import com.khanhnq.cards.exception.CardsAlreadyExistException;
import com.khanhnq.cards.exception.ResourceNotFoundException;
import com.khanhnq.cards.mapper.CardsMapper;
import com.khanhnq.cards.repository.CardsRepository;
import com.khanhnq.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findCardsByMobileNumber(mobileNumber);
        if (cards.isPresent()) {
            throw new CardsAlreadyExistException("Cards already registered with given mobile number " +mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findCardsByMobileNumber(mobileNumber);
        if (cards.isEmpty()) {
            throw new ResourceNotFoundException("Card", "mobile number", mobileNumber);
        }
        return CardsMapper.CARDS_MAPPER.mapToCardsDto(cards.get());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Optional<Cards> cards = cardsRepository.findCardsByMobileNumber(cardsDto.getMobileNumber());
        if (cards.isEmpty()) {
            throw new ResourceNotFoundException("Card", "mobile number", cardsDto.getMobileNumber());
        }
        CardsMapper.CARDS_MAPPER.updateCards(cards.get(), cardsDto);
        cardsRepository.save(cards.get());
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findCardsByMobileNumber(mobileNumber);
        if (cards.isEmpty()) {
            throw new ResourceNotFoundException("Card", "mobile number", mobileNumber);
        }
        cardsRepository.deleteCardsByMobileNumber(mobileNumber);
        return true;
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
