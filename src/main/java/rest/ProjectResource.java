package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FacadeExample;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("project")
public class ProjectResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ProjectFacade PROJECT_FACADE =  ProjectFacade.getProjectFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(PROJECT_FACADE.getAll())).build();
    }

}
