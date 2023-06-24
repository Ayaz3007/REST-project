package ru.itis.javalab.services;

import ru.itis.javalab.dto.card.CardDto;
import ru.itis.javalab.dto.card.NewCardDto;

public interface CardsService {
    void addCard(NewCardDto cardData, Long userId);
    void deleteCard(Long userId);
    CardDto getCardByUser(Long userId);
}
