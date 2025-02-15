package com.tour.prevel.wish.controller;

import com.tour.prevel.tourapi.domain.ContentCategory;
import com.tour.prevel.wish.dto.WishResponse;
import com.tour.prevel.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("{category}/{contentId}")
    public void toggleWish(
            @PathVariable String category, @PathVariable String contentId,
            Authentication auth) {
        wishService.toggleWish(ContentCategory.valueOf(category.toUpperCase()), contentId, auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<WishResponse> getWishList(Authentication auth) {
        return wishService.getWishList(auth.getName());
    }
}
