package ru.itis.javalab.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.controllers.api.AdsApi;
import ru.itis.javalab.dto.ads.AdDto;
import ru.itis.javalab.dto.ads.AdsPage;
import ru.itis.javalab.dto.ads.NewAdDto;
import ru.itis.javalab.dto.ads.UpdateAdDto;
import ru.itis.javalab.models.Ad;
import ru.itis.javalab.services.AdsService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController implements AdsApi {

    private final AdsService adsService;
    @Override
    public ResponseEntity<AdsPage> getAllAds(int page) {
        return ResponseEntity.ok().body(adsService.getAllAds(page));
    }

    @Override
    public ResponseEntity<AdsPage> searchAd(String query, int page, String category) {
        return ResponseEntity.ok(adsService.findAllByHeaderLike(query, page, category));
    }

    @Override
    public ResponseEntity<AdsPage> getAdsByCategory(int page, String category) {
        return ResponseEntity.ok(adsService.getAdsByCategory(category, page));
    }

    @Override
    public ResponseEntity<AdDto> publishAd(Long adId) {
        return ResponseEntity.accepted().body(adsService.publishAd(adId));
    }

    @Override
    public ResponseEntity<AdDto> addAd(@Valid NewAdDto newAdDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.addAd(newAdDto));
    }

    @Override
    public ResponseEntity<?> deleteAd(Long adId) {
        adsService.deleteAd(adId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<AdDto> updateAd(Long adId, @Valid UpdateAdDto updateAdDto) {
        return ResponseEntity.accepted().body(adsService.updateAd(adId, updateAdDto));
    }

    @Override
    public ResponseEntity<AdDto> getAd(Long adId) {
        return ResponseEntity.ok().body(adsService.getAd(adId));
    }
}
