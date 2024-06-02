package com.tour.prevel.auth.dto;

import lombok.Setter;

@Setter
public record LoginFormRequest(String email, String password) {
}
