package app.questionaire.org.service;

import app.questionaire.org.service.dto.ApplicationUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link app.questionaire.org.domain.ApplicationUser}.
 */
public interface ApplicationUserService {

    /**
     * Save a applicationUser.
     *
     * @param applicationUserDTO the entity to save.
     * @return the persisted entity.
     */
    ApplicationUserDTO save(ApplicationUserDTO applicationUserDTO);

    /**
     * Get all the applicationUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApplicationUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" applicationUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApplicationUserDTO> findOne(String id);

    /**
     * Delete the "id" applicationUser.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
