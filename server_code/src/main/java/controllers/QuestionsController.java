package controllers;

import ninja.Result;
import ninja.Results;

import javax.inject.Singleton;

@Singleton
public class QuestionsController {

    public Result index()
    {
        return Results.html();
    }

}
