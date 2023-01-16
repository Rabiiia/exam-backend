package facades;

import dtos.ProjectDTO;
import dtos.ProjectHourDTO;
import entities.ProjectHour;
import errorhandling.EntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectHourFacade {

    private static ProjectHourFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ProjectHourFacade() {}

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ProjectHourFacade getProjectHourFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectHourFacade();
        }
        return instance;
    }


    public List<ProjectHourDTO> getProjectsHourByDeveloperId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<ProjectHour> projectHourQuery = em.createQuery("SELECT ph FROM ProjectHour ph WHERE ph.developer.id = :did", ProjectHour.class);
            projectHourQuery.setParameter("did", id);
            List<ProjectHour> projects = projectHourQuery.getResultList();
            return ProjectHourDTO.getDtos(projects);
        } finally {
            em.close();
        }

    }

    public ProjectHourDTO delete(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();

        ProjectHour fromDB = em.find(ProjectHour.class, id);
        if (fromDB == null)
            throw new EntityNotFoundException("Could not delete, provided id " + id + " does not exist");
        em.getTransaction().begin();
        em.remove(fromDB);
        em.getTransaction().commit();
        return new ProjectHourDTO(fromDB);
    }

    public List<ProjectHourDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProjectHour> query = em.createQuery("SELECT ph FROM ProjectHour ph", ProjectHour.class);
        List<ProjectHour> projectHours = query.getResultList();
        return ProjectHourDTO.getDtos(projectHours);
    }
}
