package com.n1tjrgns.admin.controller.api;

import com.n1tjrgns.admin.controller.CrudController;
import com.n1tjrgns.admin.model.entity.Partner;
import com.n1tjrgns.admin.model.network.request.PartnerApiRequest;
import com.n1tjrgns.admin.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

}
