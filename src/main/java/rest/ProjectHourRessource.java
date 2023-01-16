package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectHourDTO;
import errorhandling.EntityNotFoundException;
import facades.DeveloperFacade;
import facades.ProjectHourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("projecthour")
public class ProjectHourRessource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ProjectHourFacade PROJECTHOUR_FACADE =  ProjectHourFacade.getProjectHourFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProjectHoursByDeveloperId(@PathParam("id") int id) {

        return Response.ok().entity(GSON.toJson(PROJECTHOUR_FACADE.getProjectsHourByDeveloperId(id))).build();
    }


    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteProjectHour(@PathParam("id") int id) throws EntityNotFoundException {
        ProjectHourDTO deleted = PROJECTHOUR_FACADE.delete(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getProjectHourByDeveloperIdAndProjectId(@QueryParam("id") int id, @QueryParam("developerId") int developerId) {
//        System.out.println(id + " " + developerId);
//        return Response.ok().entity(GSON.toJson(PROJECTHOUR_FACADE.getProjectsHourByDeveloperIdAndProjectId(id, developerId))).build();
//    }
}
