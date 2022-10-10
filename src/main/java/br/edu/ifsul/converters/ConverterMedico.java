package br.edu.ifsul.converters;

import br.edu.ifsul.clinica.model.Medico;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bernardo Dirceu Tomasi
 * 
 */
@Named(value = "converterMedico") 
@RequestScoped
public class ConverterMedico implements Serializable, Converter {
    
    @PersistenceContext(unitName = "Clinica-WebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Medico.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Medico obj = (Medico) o;
        return obj.getId().toString();
    }

}
