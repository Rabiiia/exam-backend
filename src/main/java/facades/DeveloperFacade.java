package facades;

import dtos.DeveloperDTO;
import dtos.ProjectDTO;
import entities.Developer;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeveloperFacade {

    private static DeveloperFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DeveloperFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DeveloperFacade getDeveloperFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DeveloperFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<DeveloperDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Developer> query = em.createQuery("SELECT d FROM Developer d", Developer.class);
        List<Developer> developers = query.getResultList();
        return DeveloperDTO.getDtos(developers);
    }
}
