package ru.itis.javalab.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.controllers.api.ChequesApi;
import ru.itis.javalab.dto.cheques.NewChequeDto;
import ru.itis.javalab.dto.cheques.ChequeDto;
import ru.itis.javalab.dto.cheques.ChequePageDto;
import ru.itis.javalab.services.ChequesService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ChequesController implements ChequesApi {
    private final ChequesService chequesService;

    @Override
    public ResponseEntity<ChequeDto> getCheque(Long id) {
        return ResponseEntity
                .ok(chequesService.findChequeById(id));
    }

    @Override
    public ResponseEntity<ChequePageDto> getPage(int numberOfPage) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(chequesService.getPages(numberOfPage));
    }

    @Override
    public ResponseEntity<ChequeDto> addCheque(@Valid NewChequeDto newChequeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chequesService.save(newChequeDto));
    }


    @Override
//    @Hidden
    public ResponseEntity<ChequeDto> updateCheque(Long orderId,
                                                  @Valid NewChequeDto newChequeDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(chequesService.updateChequeById(orderId, newChequeDto));
    }

    @Override
    public ResponseEntity<?> deleteCheque(Long id) {
        chequesService.deleteCheque(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<ChequePageDto> getChequesForUser(Long id, int page) {
        ChequePageDto chequePage = chequesService.getChecksByUser(page, id);
        return ResponseEntity.ok(chequePage);
    }
}
