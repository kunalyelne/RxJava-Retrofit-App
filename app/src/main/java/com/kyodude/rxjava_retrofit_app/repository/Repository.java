package com.kyodude.rxjava_retrofit_app.repository;

import com.kyodude.rxjava_retrofit_app.model.api.RetroService;

import javax.inject.Inject;

public class Repository {

    RetroService retroService;

    @Inject
    public Repository(RetroService api)
    {
        retroService = api;
    }


}
