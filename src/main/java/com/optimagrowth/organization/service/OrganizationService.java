package com.optimagrowth.organization.service;

import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization findById(String organizationId) {
        Optional<Organization> byId = organizationRepository.findByOrganizationId(organizationId);
        return byId.isPresent() ? byId.get() : null;
    }

    public Organization create(Organization organization) {
        organization = organizationRepository.save(organization);
        return organization;
    }

    public void update(Organization organization) {
        organizationRepository.save(organization);
    }

    public void delete(Organization organization) {
        organizationRepository.delete(organization);
    }
}
