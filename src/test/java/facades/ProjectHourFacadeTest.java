package facades;

import dtos.ProjectHourDTO;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;
import errorhandling.EntityNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class ProjectHourFacadeTest {

    private static EntityManagerFactory emf;
    private static ProjectHourFacade PROJECTHOUR_FACADE;

    private ProjectHour ph1, ph2;
    private Project p1, p2;

    private Developer d1, d2;

    public ProjectHourFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        PROJECTHOUR_FACADE = ProjectHourFacade.getProjectHourFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        d1 = new Developer("Ali","ali@email.com", 2);

        p1 = new Project("test","test");
        p2 = new Project("test2","test2");

        ph1 = new ProjectHour(5,"testUserStory", "testDescription", d1, p1);
        ph2 = new ProjectHour(7, "test", "test", d1, p1);


        try {
            em.getTransaction().begin();
            em.createNamedQuery("ProjectHour.deleteAllRows").executeUpdate();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            em.createNamedQuery("Developer.deleteAllRows").executeUpdate();

            em.persist(p1);
            em.persist(p1);

            em.persist(d1);

            em.persist(ph1);
            em.persist(ph2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void getProjectsHourByDeveloperId() throws Exception {
        PROJECTHOUR_FACADE.getProjectsHourByDeveloperId(d1.getId());

    }

    @Test
    void delete() throws EntityNotFoundException {
        System.out.println("Testing delete(id)");
        ProjectHourDTO ph = PROJECTHOUR_FACADE.delete(ph1.getId());
//        int expected = 1;
//        int actual = PROJECTHOUR_FACADE.getAll().size();
//        assertEquals(expected, actual);
//        assertEquals(ph,ph1);
    }




}