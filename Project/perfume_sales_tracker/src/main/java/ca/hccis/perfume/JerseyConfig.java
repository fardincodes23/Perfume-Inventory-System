/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hccis.perfume;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import ca.hccis.perfume.rest.PerfumeTransactionService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author Logan
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        registerClasses(PerfumeTransactionService.class);
    }
}
