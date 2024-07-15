package com.three.server.controller;

import com.auth0.jwt.JWT;
import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.Good;
import com.three.server.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/good")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/recommendation")
    public ApiResponse<List<Good>> recommendGoods(HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer userId = JWT.decode(token).getClaim("id").asInt();
        return ApiResponse.success(recommendationService.recommendGoods(userId));
    }
}
