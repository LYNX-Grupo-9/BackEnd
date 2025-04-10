package br.com.exemplo.crudusuariospring.controller;

import br.com.exemplo.crudusuariospring.dto.request.ReuniaoRequest;
import br.com.exemplo.crudusuariospring.dto.response.ReuniaoResponse;
import br.com.exemplo.crudusuariospring.service.ReuniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reunioes")
public class ReuniaoController {

    @Autowired
    private ReuniaoService reuniaoService;

    @PostMapping
    public ReuniaoResponse criarReuniao(@RequestBody ReuniaoRequest reuniaoRequest){
        return reuniaoService.salvarReuniao(reuniaoRequest);
    }

    @GetMapping
    public List<ReuniaoResponse> listarReuniaos(){
        return reuniaoService.listarReunioes();
    }

}
