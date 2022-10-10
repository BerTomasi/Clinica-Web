package br.edu.ifsul.dao;

import br.edu.ifsul.clinica.model.Paciente;
import br.edu.ifsul.converters.ConverterOrdem;

import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Bernardo Dirceu Tomasi
 * 
 */

@Stateful
public class PacienteDAO<TIPO> extends DAOGenerico<Paciente> implements Serializable{
    
    public PacienteDAO(){
        super();
        classePersistente = Paciente.class;
        
        listaOrdem.add(new Ordem("id", "ID","=")); 
        listaOrdem.add(new Ordem("nome", "Nome", "like")); // elemento 1
        listaOrdem.add(new Ordem("cpf", "CPF","like"));
        listaOrdem.add(new Ordem("telefone", "Telefone","like"));
        
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

}
