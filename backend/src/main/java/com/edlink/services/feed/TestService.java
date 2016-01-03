package com.edlink.services.feed;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.edlink.dto.base.PrimitiveDTO;
import com.edlink.dto.request.RequestDTO;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestService {

  @GET
  public PrimitiveDTO<String> getString(final RequestDTO requestDTO) {
    return PrimitiveDTO.wrap(requestDTO.getUserId());
  }
}
