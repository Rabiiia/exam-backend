package facades;

import dtos.ProjectDTO;
import entities.Developer;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectFacade {

    private static ProjectFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ProjectFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ProjectFacade getProjectFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public ProjectDTO create(ProjectDTO ProjectDTO){
        Project project = new Project(ProjectDTO.getName(), ProjectDTO.getDescription());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProjectDTO(project);
    }

    public ProjectDTO addDeveloperToProject(int projectId, int developerId) {
        EntityManager em = emf.createEntityManager();

        Project project = em.find(Project.class, projectId);

        Developer developer = em.find(Developer.class, developerId);
        try {
            em.getTransaction().begin();
            project.getDevelopers().add(developer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProjectDTO(project);
    }

    public List<ProjectDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
        List<Project> projects = query.getResultList();
        return ProjectDTO.getDtos(projects);
    }

}
