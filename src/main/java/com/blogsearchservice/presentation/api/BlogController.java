package com.blogsearchservice.presentation.api;

import com.blogsearchservice.application.QueryHistoryService;
import com.blogsearchservice.client.ApiClient;
import com.blogsearchservice.presentation.dto.PopularQueryResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class BlogController {
    private final ApiClient apiClient;
    private final QueryHistoryService queryHistoryService;

    public BlogController(ApiClient apiClient, QueryHistoryService queryHistoryService) {
        this.apiClient = apiClient;
        this.queryHistoryService = queryHistoryService;
    }


    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search(@RequestParam @NotBlank final String query,
                                         @RequestParam(required = false, defaultValue = "accuracy") final String sort,
                                         @RequestParam(required = false, defaultValue = "1") @Min(1) @Max(value = 50, message = "page는 50을 넘길 수 없습니다.") final int page,
                                         @RequestParam(required = false, defaultValue = "10") @Min(1) @Max(value = 50, message = "size는 50을 넘길 수 없습니다.") final int size) {

        return ResponseEntity.ok(apiClient.searchBlogs(query, sort, page, size));
    }

    @GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PopularQueryResponse>> searches() {
        return ResponseEntity.ok(queryHistoryService.findPopularQueries());
    }

}
