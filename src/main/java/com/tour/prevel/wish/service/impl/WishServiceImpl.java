package com.tour.prevel.wish.service.impl;

import com.tour.prevel.auth.repository.AuthRepository;
import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.tourapi.domain.ContentCategory;
import com.tour.prevel.wish.domain.Wish;
import com.tour.prevel.wish.dto.WishResponse;
import com.tour.prevel.wish.mapper.WishMapper;
import com.tour.prevel.wish.repository.WishQueryRepository;
import com.tour.prevel.wish.repository.WishRepository;
import com.tour.prevel.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishQueryRepository wishQueryRepository;
    private final WishRepository wishRepository;
    private final AuthRepository authRepository;
    private final WishMapper wishMapper;

    @Override
    public boolean isWished(String email, String contentId) {
        return wishQueryRepository.isWished(email, contentId);
    }

    @Override
    @Transactional
    public void toggleWish(ContentCategory category, String contentId, String userId) {
        boolean isWished = isWished(userId, contentId);

        if (isWished) {
            wishQueryRepository.deleteWish(contentId, userId);
        } else {
            wishRepository.save(Wish.builder()
                    .user(authRepository.findById(userId).get())
                    .contentId(contentId)
                    .category(category)
                    .build());
        }
    }

    @Override
    public List<WishResponse> getWishList(String userId) {
        List<Tour> tourList = wishQueryRepository.getWishList(userId);
        return wishMapper.toWishListResponses(tourList);
    }
}
