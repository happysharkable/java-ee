package ru.happyshark.java.ee.rest;

import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr findById(@PathParam("id") long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepr productRepr);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepr productRepr);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}
