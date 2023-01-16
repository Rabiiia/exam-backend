package facades;

import dtos.ProjectDTO;
import entities.Project;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class ProjectFacadeTest {

    private static EntityManagerFactory emf;
    private static ProjectFacade projectFacade;

    private Project p1, p2, p3;

    public ProjectFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        projectFacade = ProjectFacade.getProjectFacade(emf);
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
        p1 = new Project("Batman", "Create a app with batmen");
        p2 = new Project("Skill Up", "A web application");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);

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
    void create () {
        Project project = new Project("TestProject","testDescription");
        ProjectDTO actual   = projectFacade.create(new ProjectDTO(project));
        assertTrue(actual.getId()!=0);
        System.out.println(actual.getId()); //printer 3 ud.
    }

    @Test
    public void testGetAll() throws Exception {
        int actual = projectFacade.getAll().size();
        int expected = 2;
        assertEquals(expected, actual);
        System.out.println(actual);
    }
}