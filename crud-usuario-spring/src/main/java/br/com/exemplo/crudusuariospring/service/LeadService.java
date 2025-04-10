package br.com.exemplo.crudusuariospring.service;

import br.com.exemplo.crudusuariospring.dto.request.LeadRequest;
import br.com.exemplo.crudusuariospring.dto.response.LeadResponse;
import br.com.exemplo.crudusuariospring.model.Lead;
import br.com.exemplo.crudusuariospring.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public LeadResponse criarLead(LeadRequest request) {
        Lead lead = new Lead();
        lead.setNome(request.getNome());
        lead.setEmail(request.getEmail());
        lead.setTelefone(request.getTelefone());
        lead.setAssunto(request.getAssunto());
        lead.setMensagem(request.getMensagem());

        Lead leadSalva = leadRepository.save(lead);
        return new LeadResponse(leadSalva);
    }

    public List<LeadResponse> listarLeads() {
        List<Lead> leads = leadRepository.findAll();
        return leads.stream()
                .map(LeadResponse::new)
                .collect(Collectors.toList());
    }
}
