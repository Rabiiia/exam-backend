package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.DeveloperFacade;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("developer")
public class DeveloperRessource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final DeveloperFacade DEVELOPER_FACADE =  DeveloperFacade.getDeveloperFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(DEVELOPER_FACADE.getAll())).build();
    }

}
