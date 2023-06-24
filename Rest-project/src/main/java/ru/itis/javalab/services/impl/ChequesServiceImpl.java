package ru.itis.javalab.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.cheques.NewChequeDto;
import ru.itis.javalab.dto.cheques.ChequeDto;
import ru.itis.javalab.dto.cheques.ChequePageDto;
import ru.itis.javalab.exceptions.NotFoundException;
import ru.itis.javalab.models.Cheque;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.AdsRepository;
import ru.itis.javalab.repositories.ChequesRepository;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.services.ChequesService;

import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class ChequesServiceImpl implements ChequesService {
    private final ChequesRepository chequesRepository;
    private final AdsRepository adsRepository;
    private final UsersRepository usersRepository;

    @Value("${default.page-size}")
    private int defaultPageCount;

    @Override
    public ChequeDto save(NewChequeDto newChequeDto) {
        Cheque cheque = Cheque.builder()
                .price(newChequeDto.getPrice())
                .advertisement(adsRepository.findById(newChequeDto.getAdvertisementId())
                        .orElseThrow(() -> new NotFoundException("No ad with id: <" + newChequeDto.getAdvertisementId()
                                + "> found")))
                .executor(usersRepository.findById(newChequeDto.getExecutorId())
                        .orElseThrow(() -> new NotFoundException("No user with id: <" + newChequeDto.getExecutorId()
                                + "> found")))
                .state(Cheque.State.ACTIVE)
                .build();

        cheque.setPaymentTime(new Date());
        chequesRepository.save(cheque);
        return ChequeDto.from(cheque);
    }

    @Override
    public ChequeDto findChequeById(Long id) {
        return ChequeDto.from(getChequeOrThrow(id));
    }

    @Override
    public ChequePageDto getPages(int numberOfPage) {
        PageRequest pageRequest = PageRequest.of(numberOfPage, defaultPageCount);
        Page<Cheque> orderPage = chequesRepository.findAllByStateOrderById(pageRequest, Cheque.State.ACTIVE);

        return ChequePageDto.builder()
                .cheques(ChequeDto.from(orderPage.getContent()))
                .totalPageCount(orderPage.getTotalPages())
                .build();
    }

    @Override
    public ChequeDto updateChequeById(Long id, NewChequeDto orderDto) {
        Cheque cheque = getChequeOrThrow(id);

        cheque.setPrice(orderDto.getPrice());
        cheque.setExecutor(usersRepository.findById(orderDto.getExecutorId()).get());
        cheque.setAdvertisement(adsRepository.findById(orderDto.getAdvertisementId()).get());

        chequesRepository.save(cheque);

        return ChequeDto.from(cheque);
    }

    @Override
    public void deleteCheque(Long id) {
        Cheque cheque = getChequeOrThrow(id);
        cheque.setState(Cheque.State.DELETED);
        chequesRepository.save(cheque);
    }

    public Cheque getChequeOrThrow(Long id) {
        Cheque cheque = chequesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Чек с идентификатором <" + id + "> не найден"));
        if (cheque.getState().equals(Cheque.State.DELETED)) {
            throw new NotFoundException("Чек с идентификатором <" + id + "> не найден");
        }
        return cheque;
    }

    @Override
    public ChequePageDto getChecksByUser(int numberOfPage, Long userId) {
        PageRequest pageRequest = PageRequest.of(numberOfPage, defaultPageCount);
        User executor = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No user with id: <" + userId
                        + "> found"));

        Page<Cheque> chequePage = chequesRepository.findAllByExecutorAndStateOrderById(pageRequest,
                executor, Cheque.State.ACTIVE);

        return ChequePageDto.builder()
                .cheques(ChequeDto.from(chequePage.getContent()))
                .totalPageCount(chequePage.getTotalPages())
                .build();
    }
}
