package br.edu.ifsul.controle;

import br.edu.ifsul.clinica.model.Clinica;
import br.edu.ifsul.dao.ClinicaDAO;

import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bernardo Dirceu Tomasi
 * 
 */
@Named(value = "controleClinica")
@ViewScoped
public class ControleClinica implements Serializable {
    
    @EJB
    private ClinicaDAO<Clinica> dao;
    private Clinica objeto;
    
    public ControleClinica(){
        
    }
       
    public String listar(){
        return "/privado/clinica/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Clinica();        
    }
        
    public void alterar(Object id){
        try {
            objeto = dao.getObjectByID(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        } 
    }
    
    public void remover(Object id){
        try {
            objeto = dao.getObjectByID(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                    Util.getMensagemErro(e));
        }
    }

    public ClinicaDAO getDao() {
        return dao;
    }

    public void setDao(ClinicaDAO dao) {
        this.dao = dao;
    }

    public Clinica getObjeto() {
        return objeto;
    }

    public void setObjeto(Clinica objeto) {
        this.objeto = objeto;
    }

}
