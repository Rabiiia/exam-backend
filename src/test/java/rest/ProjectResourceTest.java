package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectDTO;
import entities.Developer;
import entities.Project;
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
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProjectResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Project p1, p2;
    private static Developer d1, d2;

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
        p1 = new Project("nameTest", "DescriptionTest");
        p2 = new Project("Skill Up", "A web application");

        d1 = new Developer("Ali","ali@email.com", 2);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(d1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    @Test
    public void postProjectTest() {
        ProjectDTO pdto = new ProjectDTO(p1);
        String requestBody = GSON.toJson(pdto);

        given()
                .header("Content-type", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/project")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("nameTest"));
    }

    @Test
    public void postAddDeveloperToProjectTest() {
        Project p = new Project(p1.getName(), p1.getDescription());
        Developer d = new Developer(d1.getName(), d1.getEmail(), d1.getBillingPrHour());
        p.getDevelopers().add(d);
        ProjectDTO pdto = new ProjectDTO(p);
        String requestBody = GSON.toJson(pdto);

        given()
                .header("Content-type", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/project/" + p1.getId() + "/developer/" + d1.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("nameTest"));
    }

    @Test
    public void getAll() throws Exception {
        List<ProjectDTO> projectDTOS;

        projectDTOS = given()
                .contentType("application/json")
                .when()
                .get("/project/all")
                .then()
                .extract().body().jsonPath().getList("", ProjectDTO.class);

        ProjectDTO p1DTO = new ProjectDTO(p1);
        ProjectDTO p2DTO = new ProjectDTO(p2);
        assertThat(projectDTOS, containsInAnyOrder(p1DTO, p2DTO));

    }

}
