package com.smejias.bean;

import com.smejias.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Sebastián Mejía Serna
 */

@ManagedBean
@RequestScoped
public class BeanPerson {
    private Person person = new Person();
    private static List<Person> list = new ArrayList();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }
    
    public void add(){
        this.list.add(person);
        pushNotification();
    }
    
    public void pushNotification(){
        String summary = "Nuevo Elemento";
        String detail = "Se agregó a la lista";
        String channel = "/notify";        
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(channel, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(detail)));
    }
}
