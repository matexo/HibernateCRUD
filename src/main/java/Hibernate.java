import java.util.ArrayList;
import java.util.List;

public class Hibernate {
    public static void main(String[] args)
    {
//        Person p = new Person();
//        p.setName("Abc");
//        p.setSurname("Bcd");
//        PersonDAO personDAO = new PersonDAO();
//        personDAO.create(p);
//
//        p = personDAO.read((long) 2);
//        System.out.println(p.toString());
//
//        p.setSurname("LOL");
//        p.setName("LOL");
//        System.out.println(p.toString());
//        personDAO.update((long) 3 , p );
//
//        personDAO.delete((long)4 );
//
//        Product p = new Product();
//        p.setName("A");
//        p.setCategory("B");
//        p.setPrice(20);
//        Product p2 = new Product();
//        p2.setName("Aa");
//        p2.setCategory("Ba");
//        p2.setPrice(200);
//        Person person = new Person();
//        person.setName("A");
//        person.setSurname("B");
//        List<Product> list = new ArrayList<Product>();
//        list.add(p);
//        list.add(p2);
//        person.setList(list);
//        PersonDAO personDao = new PersonDAO();
//        personDao.create(person);

        Product p1 = new Product();
        p1.setName("AA");
        p1.setCategory("AA");
        p1.setPrice(10);

        Product p2 = new Product();
        p2.setName("AA");
        p2.setCategory("AA");
        p2.setPrice(10);


        List<Product> list = new ArrayList<Product>();
        list.add(p1);
        List<Product> list1 = new ArrayList<Product>();
        list1.add(p2);
        list1.add(p2);


        Person p = new Person();
        p.setName("A");
        p.setSurname("B");
        p.setAdress("C");
        p.setList(list);
        Person pp = new Person();
        pp.setName("A");
        pp.setSurname("B");
        pp.setAdress("C");
        pp.setList(list1);
        System.out.println(p.equals(pp));
    }

}
