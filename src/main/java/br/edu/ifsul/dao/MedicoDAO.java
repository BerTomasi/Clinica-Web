package br.edu.ifsul.dao;

import br.edu.ifsul.clinica.model.Medico;
import br.edu.ifsul.converters.ConverterOrdem;

import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Bernardo Dirceu Tomasi
 * 
 */
@Stateful
public class MedicoDAO<TIPO> extends DAOGenerico<Medico> implements Serializable{
    
    public MedicoDAO(){
        super();
        classePersistente = Medico.class;
        
        listaOrdem.add(new Ordem("id", "ID","=")); 
        listaOrdem.add(new Ordem("nome", "Nome", "like")); // elemento 1
        listaOrdem.add(new Ordem("especialidade.nome", "Especialidade","like"));
        listaOrdem.add(new Ordem("clinica.nome", "Nome - Clinica","like"));
        listaOrdem.add(new Ordem("crm", "CRM","like"));
        listaOrdem.add(new Ordem("cpf", "CPF","like"));
        listaOrdem.add(new Ordem("telefone", "Telefone","like"));
        
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

}
