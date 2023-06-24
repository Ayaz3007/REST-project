package ru.itis.javalab.services;

import ru.itis.javalab.dto.cheques.NewChequeDto;
import ru.itis.javalab.dto.cheques.ChequeDto;
import ru.itis.javalab.dto.cheques.ChequePageDto;

import java.util.Optional;

public interface ChequesService {
    ChequeDto save(NewChequeDto newChequeDto);

    ChequeDto findChequeById(Long id);

    ChequePageDto getPages(int numberOfPage);

    ChequeDto updateChequeById(Long id, NewChequeDto orderDto);

    void deleteCheque(Long id);
    ChequePageDto getChecksByUser(int numberOfPage, Long userId);
}
