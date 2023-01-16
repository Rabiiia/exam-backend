package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectDTO;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author lam@cphbusiness.dk
 */
@Path("project")
public class ProjectResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ProjectFacade PROJECT_FACADE =  ProjectFacade.getProjectFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String jsonInput)  {
        ProjectDTO p = GSON.fromJson(jsonInput, ProjectDTO.class);
        ProjectDTO pNew = PROJECT_FACADE.create(p);
        return Response.ok().entity(GSON.toJson(pNew)).build();
    }

    @POST
    @Path("{projectId}/developer/{developerId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addHarbourToBoat(@PathParam("projectId") int projectId, @PathParam("developerId") int developerId){
        System.out.println(projectId + " " + developerId);
        ProjectDTO returned = PROJECT_FACADE.addDeveloperToProject(projectId, developerId);
        System.out.println(returned.toString());

        return Response.ok().entity(GSON.toJson(returned)).build();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(PROJECT_FACADE.getAll())).build();
    }



//    @Path("/populate")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public void populate() {
//        Populator.populate();
//    }

}