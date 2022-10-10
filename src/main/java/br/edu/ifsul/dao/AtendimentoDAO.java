package br.edu.ifsul.dao;

import br.edu.ifsul.clinica.model.Atendimento;
import br.edu.ifsul.converters.ConverterOrdem;

import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Bernardo Dirceu Tomasi
 * 
 */

@Stateful
public class AtendimentoDAO<TIPO> extends DAOGenerico<Atendimento> implements Serializable{
    
    public AtendimentoDAO(){
        super();
        classePersistente = Atendimento.class;
        
        listaOrdem.add(new Ordem("id", "ID","="));
        listaOrdem.add(new Ordem("medico.clinica.nome", "Nome - Clinica", "like"));
        listaOrdem.add(new Ordem("medico.especialidade.nome", "Especialidade", "like"));
        listaOrdem.add(new Ordem("medico.nome", "Nome - Médico", "like"));
        listaOrdem.add(new Ordem("paciente.nome", "Nome - Paciente", "like"));
        listaOrdem.add(new Ordem("paciente.CPF", "CPF - Paciente", "like"));
        //listaOrdem.add(new Ordem("data", "Data", "like"));
        
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

}
