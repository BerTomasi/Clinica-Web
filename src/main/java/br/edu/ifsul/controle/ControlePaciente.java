package br.edu.ifsul.controle;

import br.edu.ifsul.clinica.model.Paciente;
import br.edu.ifsul.dao.PacienteDAO;

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
@Named(value = "controlePaciente")
@ViewScoped
public class ControlePaciente implements Serializable {
    
    @EJB
    private PacienteDAO<Paciente> dao;
    private Paciente objeto;
    
    public ControlePaciente(){
        
    }
       
    public String listar(){
        return "/privado/paciente/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Paciente();        
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

    public PacienteDAO getDao() {
        return dao;
    }

    public void setDao(PacienteDAO dao) {
        this.dao = dao;
    }

    public Paciente getObjeto() {
        return objeto;
    }

    public void setObjeto(Paciente objeto) {
        this.objeto = objeto;
    }

}
