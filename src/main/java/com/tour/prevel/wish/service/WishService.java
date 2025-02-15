package com.tour.prevel.wish.service;

import com.tour.prevel.tourapi.domain.ContentCategory;
import com.tour.prevel.wish.dto.WishResponse;

import java.util.List;

public interface WishService {

    boolean isWished(String email, String contentId);

    void toggleWish(ContentCategory category, String contentId, String userId);

    List<WishResponse> getWishList(String userId);
}
