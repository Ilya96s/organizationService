package com.optimagrowth.organization.service;

import com.optimagrowth.organization.dto.ActionEnum;
import com.optimagrowth.organization.events.source.SimpleSourceBean;
import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final SimpleSourceBean simpleSourceBean;

    public Organization findById(String organizationId) {
        Optional<Organization> byId = organizationRepository.findByOrganizationId(organizationId);
        return byId.isPresent() ? byId.get() : null;
    }

    public Organization create(Organization organization) {
        organization.setOrganizationId(UUID.randomUUID().toString());
        organization = organizationRepository.save(organization);
        simpleSourceBean.publishOrganizationChange(ActionEnum.CREATED.name(), organization.getOrganizationId());
        return organization;
    }

    public void update(Organization organization) {
        organizationRepository.save(organization);
    }

    public void delete(Organization organization) {
        organizationRepository.delete(organization);
    }
}
