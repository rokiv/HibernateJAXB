package jaxb;

import model.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
public class Users implements Serializable {

    List<User> list;

    public List<User> getList() {
        return list;
    }

    @XmlElement(name = "user")
    public void setList(List<User> list) {
        this.list = list;
    }
}