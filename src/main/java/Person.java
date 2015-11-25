import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Matexo on 2015-11-15.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    Long id;
    private String name;
    private String surname;
    private String adress;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    @Cascade(CascadeType.ALL)
    private List<Product> list;

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
        public boolean equals( Object other)
    {
        if(other == null) return false;
        if(other == this) return true;
        if(!(other instanceof Person)) return false;
        Person person = (Person) other;
        if( person.getSurname().equals(this.surname)
          && person.getName().equals(this.name)
          && person.getAdress().equals(this.adress))
        {
            if(person.getList() == null && this.list == null) return true;
            else if(person.getList() == null || this.list == null) return false;
            else if(person.getList().equals(this.list)) return true;
            else return false;
        }
            return false;
    }
}
