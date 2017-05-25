/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import entidade.Piada;
import entidade.PiadaDAO;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PiadaController {
    
    
    @CrossOrigin(origins = {"http://editor.swagger.io", "http://localhost:8080"})
    @RequestMapping(method = RequestMethod.POST,
                    value="/piada")
    public Piada salvarPiada(@RequestBody Piada p){
        System.out.println("Autordapiada:"+p.getAutor().getNome());
        PiadaDAO dao = new PiadaDAO();
        Piada piadaSalva = dao.salvar(p);
        return piadaSalva;
    }
  
    
    
    @CrossOrigin(origins = {"http://editor.swagger.io", "http://localhost:8080"})
    @RequestMapping(method=RequestMethod.GET,
                    value="/piada")
    public List<Piada> listarPiadas(@RequestParam(value = "palavra", defaultValue="") String filtro, 
                                    @RequestParam(value = "safe", defaultValue="false") boolean safe){
        PiadaDAO dao = new PiadaDAO();
        return dao.consultar(filtro, safe);
    }
    
    
    @CrossOrigin(origins = {"http://editor.swagger.io", "http://localhost:8080"})
    @RequestMapping(method=RequestMethod.DELETE,
                    value="/piada/{id}")
    public void deletar(@PathVariable("id")int id){
        new PiadaDAO().deletar(id);
    }
    
    
    
    
    
}
