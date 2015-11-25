import org.junit.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Matexo on 2015-11-17.
 */
public class CRUDTest {

    public PersonDAO personDAO = new PersonDAO();
    private Person testPersonWithoutProducts;
    private Person testPersonWithProducts;

    private Product testProduct1;
    private Product testProduct2;


    //trzeba jakas zainicjowac konfiguracje hbn przed testowaniem

    @Before
    public void before()
    {
        testPersonWithoutProducts = new Person();
        testPersonWithoutProducts.setName("Adam");
        testPersonWithoutProducts.setSurname("Adamowicz");
        testPersonWithoutProducts.setAdress("Adamska 11");

        testPersonWithProducts = new Person();
        testPersonWithProducts.setName("Andrzej");
        testPersonWithProducts.setSurname("Andrzejowicz");
        testPersonWithProducts.setAdress("Andrzejowicka");

        testProduct1 = new Product();
        testProduct1.setName("1");
        testProduct1.setCategory("1");
        testProduct1.setPrice(1);

        testProduct2 = new Product();
        testProduct2.setName("2");
        testProduct2.setCategory("2");
        testProduct2.setPrice(2);

        List<Product> list = new ArrayList<Product>();
        list.add(testProduct1);
        list.add(testProduct2);

        testPersonWithProducts.setList(list);
    }

    @Test
    @Ignore
    public void addingPersonWithoutProducts()
    {
        Long id = personDAO.create(testPersonWithoutProducts);
        Person personFromDB = personDAO.read(id);

        Assert.assertEquals( testPersonWithoutProducts.getId() , personFromDB.getId());
        Assert.assertEquals( testPersonWithoutProducts.getName() , personFromDB.getName());
        Assert.assertEquals( testPersonWithoutProducts.getSurname() , personFromDB.getSurname());
        Assert.assertEquals( testPersonWithoutProducts.getAdress() , personFromDB.getAdress());
        Assert.assertEquals( testPersonWithoutProducts.getList() , null);
    }

    @Test
    @Ignore
    public void addingPersonWithProducts()
    {
        Long id = personDAO.create(testPersonWithProducts);
        Person personFromDB = personDAO.read(id);

        Assert.assertEquals( testPersonWithProducts.getId() , personFromDB.getId());
        Assert.assertEquals( testPersonWithProducts.getName() , personFromDB.getName());
        Assert.assertEquals( testPersonWithProducts.getSurname() , personFromDB.getSurname());
        Assert.assertEquals( testPersonWithProducts.getAdress() , personFromDB.getAdress());
        Assert.assertNotNull(testPersonWithProducts.getList());
        Assert.assertEquals( testPersonWithProducts.getList().size() , personFromDB.getList().size());
    }

    @Test
    @Ignore
    public void addingPersonWith() // wymyslic jakas ciekawa nazwe
    {
        Long myId = 9999L;
        testPersonWithProducts.setId( myId );

        Long id = personDAO.create(testPersonWithProducts);

        Assert.assertNotSame(myId , id);
    }

    @Test(timeout = 500)
    @Ignore
    public void addingMultipleRecordToDB()
    {
        for(int i = 0 ; i < 100 ; i++ )
        {
            Person p = new Person();
            p.setName("Adam" + i);
            p.setSurname("Adamowicz" + i);
            p.setAdress("Adamska" + i);
            personDAO.create(p);
        }
    }

    @Test
    @Ignore
    public void add2PersonWithTheSameData()
    {
        Person p = new Person();
        p.setName("A");
        p.setSurname("B");
        p.setAdress("C");
        Person pp = new Person();
        pp.setName("A");
        pp.setSurname("B");
        pp.setAdress("C");

        Long id1 = personDAO.create(p);
        Long id2 = personDAO.create(pp);

        Assert.assertNotSame(id1 , id2);
    }

    @Test(expected = NullPointerException.class)
    @Ignore
    public void readPersonWhoDoesntExistInDB()
    {
        personDAO.read(new Long(99999));
    }


    @Test(expected = NullPointerException.class)
    @Ignore
    public void editPersonWhoDosntExsistInDB()
    {
        personDAO.update(new Long(999999) , testPersonWithProducts);
    }

    @Test
    @Ignore
    public void editingPersonInformation()
    {
        Long id = personDAO.create(testPersonWithoutProducts);

        Person newPerson = new Person();
        newPerson.setName("Artur");
        newPerson.setSurname("Arturowicz");
        newPerson.setAdress("Arturowicka");
        personDAO.update(id , newPerson);

        Assert.assertNotSame(testPersonWithoutProducts.getName() , newPerson.getName());
        Assert.assertNotSame(testPersonWithoutProducts.getSurname() , newPerson.getSurname());
        Assert.assertNotSame(testPersonWithoutProducts.getAdress() , newPerson.getAdress());
    }

    @Test
    @Ignore
    //TODO
    public void editingProductsFromPerson()
    {
        Product newProduct = new Product();
        newProduct.setName("NEW");
        newProduct.setCategory("NEW");
        newProduct.setPrice(123);
        int index = 0;

        Long id = personDAO.create(testPersonWithProducts);
        Person personFromDB = personDAO.read(id);

        List<Product> oldList = personFromDB.getList();

        Product oldProduct = oldList.get(index);
        oldList.set(index , newProduct);

        //Product newwProduct = oldList.get(index);
        //Assert.assertEquals(oldProduct.getName() , newwProduct.getName());

        personDAO.update(id , personFromDB);

        personFromDB = personDAO.read(id);
        Product productFromDB = personFromDB.getList().get(index);

        Assert.assertNotSame(oldProduct.getName() , productFromDB.getName());
        Assert.assertNotSame(oldProduct.getCategory() , productFromDB.getCategory());
        Assert.assertNotSame(oldProduct.getPrice() , productFromDB.getPrice());



    }

    @Test(expected = NullPointerException.class)
    @Ignore
    public void deletePersonWithProducts()
    {
        Long id = personDAO.create(testPersonWithProducts);
        personDAO.delete(id);
        personDAO.read(id);
    }


}
