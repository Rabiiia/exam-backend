package facades;

import dtos.ProjectDTO;
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


    public List<ProjectDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
        List<Project> projects = query.getResultList();
        return ProjectDTO.getDtos(projects);
    }

}
