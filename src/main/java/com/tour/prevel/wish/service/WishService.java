package com.tour.prevel.wish.service;

import com.tour.prevel.tourapi.domain.ContentCategory;

public interface WishService {

    boolean isWished(String email, String contentId);

    void toggleWish(ContentCategory category, String contentId, String userId);
}
