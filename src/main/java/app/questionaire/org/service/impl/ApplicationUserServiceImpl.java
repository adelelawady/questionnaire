package app.questionaire.org.service.impl;

import app.questionaire.org.service.ApplicationUserService;
import app.questionaire.org.domain.ApplicationUser;
import app.questionaire.org.repository.ApplicationUserRepository;
import app.questionaire.org.service.dto.ApplicationUserDTO;
import app.questionaire.org.service.mapper.ApplicationUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ApplicationUser}.
 */
@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserServiceImpl.class);

    private final ApplicationUserRepository applicationUserRepository;

    private final ApplicationUserMapper applicationUserMapper;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository, ApplicationUserMapper applicationUserMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserMapper = applicationUserMapper;
    }

    @Override
    public ApplicationUserDTO save(ApplicationUserDTO applicationUserDTO) {
        log.debug("Request to save ApplicationUser : {}", applicationUserDTO);
        ApplicationUser applicationUser = applicationUserMapper.toEntity(applicationUserDTO);
        applicationUser = applicationUserRepository.save(applicationUser);
        return applicationUserMapper.toDto(applicationUser);
    }

    @Override
    public Page<ApplicationUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationUsers");
        return applicationUserRepository.findAll(pageable)
            .map(applicationUserMapper::toDto);
    }


    @Override
    public Optional<ApplicationUserDTO> findOne(String id) {
        log.debug("Request to get ApplicationUser : {}", id);
        return applicationUserRepository.findById(id)
            .map(applicationUserMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ApplicationUser : {}", id);
        applicationUserRepository.deleteById(id);
    }
}
