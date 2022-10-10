package br.edu.ifsul.controle;


import br.edu.ifsul.clinica.model.Clinica;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.EspecialidadeDAO;


import br.edu.ifsul.clinica.model.Medico;
import br.edu.ifsul.clinica.model.Especialidade;
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
@Named(value = "controleMedico")
@ViewScoped
public class ControleMedico implements Serializable {
    
    @EJB
    private MedicoDAO<Medico> dao;
    private Medico objeto;
    
    @EJB
    private EspecialidadeDAO<Especialidade> daoEspecialidade;
    
    @EJB
    private ClinicaDAO<Clinica> daoClinica;
    
    public ControleMedico(){
        
    }
       
    public String listar(){
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Medico();        
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

    public MedicoDAO getDao() {
        return dao;
    }

    public void setDao(MedicoDAO dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoEspecialidade
     */
    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    /**
     * @param daoEspecialidade the daoEspecialidade to set
     */
    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }

    /**
     * @return the daoClinica
     */
    public ClinicaDAO<Clinica> getDaoClinica() {
        return daoClinica;
    }

    /**
     * @param daoClinica the daoClinica to set
     */
    public void setDaoClinica(ClinicaDAO<Clinica> daoClinica) {
        this.daoClinica = daoClinica;
    }

}
