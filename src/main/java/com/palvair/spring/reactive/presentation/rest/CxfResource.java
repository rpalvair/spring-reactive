package com.palvair.spring.reactive.presentation.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/cxf")
@Api(value = "/cxf", description = "")
public class CxfResource {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(
            value = "",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "",
                    response = String.class
            )
    })
    public Response hello() {
        return Response.ok("Hello I'm cxf resource").build();
    }
}
