package com.optimagrowth.organization.events.source;

import com.optimagrowth.organization.events.model.OrganizationChangeModel;
import com.optimagrowth.organization.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleSourceBean {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrganizationChange(String action, String organizationId) {
        log.info("Sending kafka message {} for Organization Id: {}",
                action, organizationId);
        OrganizationChangeModel changeModel = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                organizationId,
                UserContext.getCorrelationId());

        kafkaTemplate.send("test-topic", changeModel.getOrganizationId(), changeModel);
    }
}
