package com.tour.prevel.auth.dto;

public record CreateUserRequest(String email, String password, String passwordConfirm, String nickname) {
}
