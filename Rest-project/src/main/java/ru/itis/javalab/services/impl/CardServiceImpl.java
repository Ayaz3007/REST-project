package ru.itis.javalab.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.card.CardDto;
import ru.itis.javalab.dto.card.NewCardDto;
import ru.itis.javalab.exceptions.NotFoundException;
import ru.itis.javalab.models.PaymentCard;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.CardsRepository;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.services.CardsService;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;
    private final UsersRepository usersRepository;

    @Override
    public void addCard(NewCardDto cardData, Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("No user with id: <" + userId + "> found"));

        PaymentCard card = PaymentCard.builder()
                .cardNumber(cardData.getCardNumber())
                .CVV(cardData.getCVV())
                .ownerName(cardData.getOwnerName())
                .build();

        user.setCard(card);
        cardsRepository.save(card);
        usersRepository.save(user);

    }

    @Override
    public void deleteCard(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("No user with id: <" + userId + "> found"));

        PaymentCard card = cardsRepository.findById(user.getCard().getId()).orElseThrow(
                () -> new NotFoundException("No card with id: <" + userId + "> found"));

        user.setCard(null);
        cardsRepository.delete(card);
    }

    @Override
    public CardDto getCardByUser(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("No user with id: <" + userId + "> found"));

        if(user.getCard() == null) {
            throw new NotFoundException("User with id <" + userId + "> hasn't card");
        }

        return CardDto.builder()
                .id(user.getCard().getId())
                .cardNumber(user.getCard().getCardNumber())
                .CVV(user.getCard().getCVV())
                .ownerName(user.getCard().getOwnerName())
                .build();
    }
}
