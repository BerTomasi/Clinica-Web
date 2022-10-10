package br.edu.ifsul.controle;

import br.edu.ifsul.clinica.model.Paciente;
import br.edu.ifsul.dao.PacienteDAO;

import br.edu.ifsul.clinica.model.Atendimento;
import br.edu.ifsul.dao.AtendimentoDAO;

import br.edu.ifsul.clinica.model.Clinica;
import br.edu.ifsul.dao.ClinicaDAO;

import br.edu.ifsul.clinica.model.Medico;
import br.edu.ifsul.dao.MedicoDAO;

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
@Named(value = "controleAtendimento")
@ViewScoped
public class ControleAtendimento implements Serializable {

    @EJB
    private AtendimentoDAO<Atendimento> dao;
    private Atendimento objeto;

    @EJB
    private PacienteDAO<Paciente> daoPaciente;

    @EJB
    private MedicoDAO<Medico> daoMedico;

    public ControleAtendimento() {

    }

    public String listar() {
        return "/privado/atendimento/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Atendimento();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void remover(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            
            if ((objeto.getMedico().getCPF()).equals((objeto.getPaciente().getCPF()))) {
                Util.mensagemErro("O PACIENTE E O MÉDICO NÃO PODEM SER A MESMA PESSOA");
            } else {
                if (objeto.getId() == null) {
                    dao.persist(objeto);
                } else {
                    dao.merge(objeto);
                }
                Util.mensagemInformacao("Objeto persistido com sucesso!");
            }
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public AtendimentoDAO getDao() {
        return dao;
    }

    public void setDao(AtendimentoDAO dao) {
        this.dao = dao;
    }

    public Atendimento getObjeto() {
        return objeto;
    }

    public void setObjeto(Atendimento objeto) {
        this.objeto = objeto;
    }

    public PacienteDAO<Paciente> getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO<Paciente> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    /**
     * @return the daoMedico
     */
    public MedicoDAO<Medico> getDaoMedico() {
        return daoMedico;
    }

    /**
     * @param daoMedico the daoMedico to set
     */
    public void setDaoMedico(MedicoDAO<Medico> daoMedico) {
        this.daoMedico = daoMedico;
    }

}
