package com.priyaaank.dspatterns.bookmarksmanager.bookmarks.controller;

import com.priyaaank.dspatterns.bookmarksmanager.bookmarks.domain.Bookmark;
import com.priyaaank.dspatterns.bookmarksmanager.bookmarks.presenter.BookmarksPresenter;
import com.priyaaank.dspatterns.bookmarksmanager.bookmarks.service.EnrichBookmarksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/bookmark")
public class BookmarksController {

    private EnrichBookmarksService enrichBookmarksService;
    private RestTemplate restTemplate;

    @Autowired
    public BookmarksController(EnrichBookmarksService enrichBookmarksService, RestTemplate restTemplate) {
        this.enrichBookmarksService = enrichBookmarksService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/enrich")
    public BookmarksPresenter createBookmark(@RequestParam String fieldsRequested, @RequestParam String url) {
        Bookmark bookmark = this.enrichBookmarksService.enrichBookmark(fieldsRequested, new Bookmark(url));
        log.info("Bookmark {}", bookmark);
        return BookmarksPresenter.fromDomain(bookmark);
    }

}
