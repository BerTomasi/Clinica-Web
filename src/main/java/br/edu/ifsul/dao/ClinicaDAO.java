package br.edu.ifsul.dao;

import br.edu.ifsul.clinica.model.Clinica;
import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.dao.DAOGenerico;
//import br.edu.ifsul.converters.ConverterOrdem;

import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Bernardo Dirceu Tomasi
 * 
 */
@Stateful
public class ClinicaDAO <TIPO> extends DAOGenerico<Clinica> implements Serializable{
    
    public ClinicaDAO(){
        super();
        classePersistente = Clinica.class;
        
        listaOrdem.add(new Ordem("id", "ID","="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("cidade", "Cidade", "like"));
        listaOrdem.add(new Ordem("telefone", "Telefone", "like"));
        
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}
