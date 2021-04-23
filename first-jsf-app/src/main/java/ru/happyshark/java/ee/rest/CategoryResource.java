package ru.happyshark.java.ee.rest;

import ru.happyshark.java.ee.service.repr.CategoryRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Local
@Path("/v1/category")
public interface CategoryResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryRepr categoryRepr);
}
