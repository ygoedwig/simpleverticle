/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rr.fdm.simple.verticle;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public class MyFirstVerticle extends AbstractVerticle {

    public static void main(String[] args) throws IOException {
        JsonObject config = new JsonObject();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyFirstVerticle());
    }

    @Override
    public void start(Future<Void> fut) {

        Router router = Router.router(vertx);
        router.get("/:id").handler(this::handle);
        router.get("/:id/run").handler(this::run);
        router.get("/").handler(this::root);
        System.out.println("Starting to listen on port 8430 go for it.");
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8430, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }

    private void root(RoutingContext routingContext) {
        routingContext.response().end("You have run the simple verticle");
    }

    private void handle(RoutingContext routingContext) {
        String id = routingContext.request().getParam("id");
        routingContext.response().end("From Java " + id);
    }

    private void run(RoutingContext routingContext) {
        String id = routingContext.request().getParam("id");
        ObjectMapper mapper = new ObjectMapper();
        JsonData data = new JsonData();
        data.setId(id);
        data.setCaseNum(10);
        data.setTitle("hellow");

        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            routingContext.response().end(json);
        } catch (Exception e) {
        }
    }

}
