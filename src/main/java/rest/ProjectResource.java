package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectDTO;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(PROJECT_FACADE.getAll())).build();
    }

}
