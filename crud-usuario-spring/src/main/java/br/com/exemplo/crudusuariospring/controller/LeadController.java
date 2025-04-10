package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.LeadRequest;
import br.com.exemplo.crudusuariospring.dto.response.LeadResponse;
import br.com.exemplo.crudusuariospring.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping
    public LeadResponse criarLead(@RequestBody LeadRequest leadRequest) {
        return leadService.criarLead(leadRequest);
    }

    @GetMapping
    public List<LeadResponse> listarLeads() {
        return leadService.listarLeads();
    }
}
