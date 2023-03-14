package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.TestDTO;
import at.fh.joanneum.irfc.persistence.entiy.TestEntity;
import at.fh.joanneum.irfc.service.TestService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
@Path("test")
public class TestApi {

  @Inject
  TestService testService;

  @GET
  @Path("subpath")
  @Produces(MediaType.APPLICATION_JSON)
  public TestDTO test(){
    TestDTO toReturn = new TestDTO();
    toReturn.setName("test");
    return toReturn;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response create(@PathParam("id")Long id, TestDTO testDTO){
    try {
    TestDTO newDto = testService.testMethod(testDTO);

    TestEntity testEntity = new TestEntity();

    return Response.ok(newDto).build();

    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }




}
