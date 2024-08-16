package com.tour.prevel.wish.service.impl;

import com.tour.prevel.wish.repository.WishQueryRepository;
import com.tour.prevel.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishQueryRepository wishQueryRepository;

    @Override
    public boolean isWished(String email, String contentId) {
        return wishQueryRepository.isWished(email, contentId);
    }
}
