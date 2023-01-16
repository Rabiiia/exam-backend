package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProjectHourRessourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private ProjectHour ph1, ph2;
    private Project p1, p2;

    private Developer d1, d2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }



    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        d1 = new Developer("Ali","ali@email.com", 2);

        p1 = new Project("test","test");
        p2 = new Project("test2","test2");

        ph1 = new ProjectHour(5,"testUserStory", "testDescription", d1, p1);
       // ph2 = new ProjectHour(7, "test", "test", d1, p1);


        try {
            em.getTransaction().begin();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            em.createNamedQuery("Developer.deleteAllRows").executeUpdate();
            em.createNamedQuery("ProjectHour.deleteAllRows").executeUpdate();

            em.persist(p1);
            em.persist(p1);

            em.persist(d1);

            em.persist(ph1);
           // em.persist(ph2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testDeleteProjectHour() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", ph1.getId())
                .delete("/projecthour/{id}")
                .then()
                .statusCode(200)
                .body("id",equalTo(ph1.getId()));
    }

}
