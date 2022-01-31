package com.example.demo.resource;

import com.example.demo.domain.HttpResponse;
import com.example.demo.domain.Pais;
import com.example.demo.exception.domain.NomeJaExistenteException;
import com.example.demo.exception.domain.PaisNaoEncontradoException;
import com.example.demo.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/", "/pais"})
public class PaisResource {
    private static final String PAIS_APAGADO_COM_SUCESSO = "Pais apgado com sucesso";
    private PaisService paisService;

    @Autowired
    public PaisResource(PaisService paisService) {
        this.paisService = paisService;
    }

    @PostMapping("/registar")
    public ResponseEntity<Pais> registar(@RequestBody Pais pais) throws PaisNaoEncontradoException, NomeJaExistenteException {
        Pais newPais = paisService.registar(pais.getNome(), pais.getCapital(), pais.getRegiao(), pais.getSub_regiao(), pais.getArea());
        return new ResponseEntity<>(newPais, OK);
    }
    @PostMapping("/actualizar")
    public ResponseEntity<Pais> actualizar(@RequestParam("currentNome") String currentNome,
                                           @RequestParam("nome") String nome,
                                           @RequestParam("capital") String capital,
                                           @RequestParam("regiao") String regiao,
                                           @RequestParam("sub_regiao") String sub_regiao,
                                           @RequestParam("area") String area) throws PaisNaoEncontradoException, NomeJaExistenteException {
        Pais actualizarPais = paisService.actualizarPais(currentNome, nome, capital, regiao, sub_regiao, area );
        return new ResponseEntity<>(actualizarPais, OK);
    }
    @GetMapping("/find/{nome}")
    public ResponseEntity<Pais> getPais(@PathVariable("nome") String nome) {
        Pais pais = paisService.findPaisByNome(nome);
        return new ResponseEntity<>(pais, OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Pais>> getAllPaises() {
        List<Pais> paises = paisService.getPaises();
        return new ResponseEntity<>(paises, OK);
    }
    @DeleteMapping("/apagar/{nome}")
    public ResponseEntity<HttpResponse> apagarPais(@PathVariable("nome") String nome) {
        paisService.apagarPais(nome);
        return response(OK, PAIS_APAGADO_COM_SUCESSO);
    }
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
