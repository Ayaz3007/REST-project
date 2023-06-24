package ru.itis.javalab.services;

import ru.itis.javalab.dto.ads.AdDto;
import ru.itis.javalab.dto.ads.AdsPage;
import ru.itis.javalab.dto.ads.NewAdDto;
import ru.itis.javalab.dto.ads.UpdateAdDto;

public interface AdsService {
    AdsPage getAllAds(int page);

    AdDto addAd(NewAdDto newAdDto);

    AdDto getAd(Long id);

    AdDto updateAd(Long id, UpdateAdDto updateAdDto);

    void deleteAd(Long id);

    AdDto publishAd(Long id);

    AdDto acceptAd(Long id);

    AdDto blockAd(Long id);

    AdsPage findAllByHeaderLike(String query, int page, String category);

    AdsPage getAdsByCategory(String category, int page);
}
