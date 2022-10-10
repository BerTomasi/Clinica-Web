package br.edu.ifsul.dao;

import br.edu.ifsul.clinica.model.Especialidade;
import br.edu.ifsul.converters.ConverterOrdem;
//import br.edu.ifsul.converters.ConverterOrdem;

import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Bernardo Dirceu Tomasi
 * 
 */
@Stateful
public class EspecialidadeDAO <TIPO> extends DAOGenerico<Especialidade> implements Serializable{
    
    public EspecialidadeDAO(){
        super();
        classePersistente = Especialidade.class;
        
        listaOrdem.add(new Ordem("id", "ID","=")); 
        listaOrdem.add(new Ordem("nome", "Nome", "like")); // elemento 1
        
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}
