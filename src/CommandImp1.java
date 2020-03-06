


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;





/**
 * this class implements interface Command and use collection from class CollectionOrganization.
 * @see CreateElement
 * @see Work
 * @see CollectionOrganization
 * @see Command
 */
public class CommandImp1 implements Command{


    /**
     * method outputs information about the received collection
     * @param collection this collection TreeSet from class Collection
     */
    @Override
    public void info(CollectionOrganization collection) {
        System.out.println("коллекция: " + collection.getCollection().getClass().getSimpleName());
        System.out.println("дата инициализации: " + collection.date.toString());
        System.out.println("колличество елементов: " + collection.getCollection().size());
        if(collection.getCollection().size() != 0){
            System.out.println("последний элемент коллекции: " + collection.getCollection().last().toString());
            System.out.println("первый элемент коллекции: " + collection.getCollection().first().toString());
        }
    }


    /**
     * this method outputs information about element collection TreeSet
     * @param collection this collection TreeSet from class Collection
     */
    @Override
    public void show(CollectionOrganization collection) {
        for(Organization org: collection.getCollection()){
            System.out.println(org);
        }
    }


    /**
     * this method adds new element to the collection TreeSet
     * @param collection this collection TreeSet from class Collection
     * @param reader this parameter can implement both keyboard input and reading from a file
     * @param realization  this parameter is enum Work.Realization. it parameter specifies how the work is performed, either interactively or using a script from a file
     */
    public void add(CollectionOrganization collection, BufferedReader reader, Work.Realization realization) {
        Organization org = CreateElement.createElement(reader, realization);
        if(org != null)
            collection.getCollection().add(org);
    }

    /**
     * this method updates the element by its name
     * @param collection this collection TreeSet from class Collection
     * @param element this name the given element
     */
    @Override
    public void updateElement(CollectionOrganization collection, String element) {
        System.out.println("елемент " + element + " Обновлен (нет)");
    }


    /**
     * this method removes the element by its name
     * @param collection this collection TreeSet from class Collection
     * @param id this id the given element
     * @return return true if element was removed, and return false if element wasn't removed
     */
    @Override
    public boolean remove(CollectionOrganization collection, long id) {

        for (Organization o: collection.getCollection()){
            if (o.getId() == id) {
                collection.getCollection().remove(o);
                return true;
            }
        }
        return false;
    }


    /**
     * this method clears collection
     * @param collection this collection TreeSet from class Collection
     */
    @Override
    public void clear(CollectionOrganization collection) {
        collection.getCollection().clear();
    }


    /**
     * this method saves collection
     * @param collection this collection TreeSet from class Collection
     * @return return true if element was saved, and return false if element wasn't saved
     */
    @Override
    public boolean save(CollectionOrganization collection) { // C://Users/Daniil/Desktop/Универ/Прога/lab5/File/
        try {

            PrintWriter printWriter = new PrintWriter(new FileOutputStream("start.json"));

                String str = Lab5.GSON.toJson(collection);
                printWriter.print(str);


            printWriter.close();
            return true;
        }catch(IOException e){
            System.out.println("Аыыы, не получилось сохранить, попробуйте воспользоваться save_as");
            return false;
        }
    }


    /**
     * this method saves elements collection specified to the file
     * @param collection this collection TreeSet from class Collection
     * @param FileName is a path to the file
     * @return return true if element was saved, and return false if element wasn't saved
     */
    @Override
    public boolean saveAs(CollectionOrganization collection, String FileName){
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(FileName));
            String str = Lab5.GSON.toJson(collection);
            printWriter.print(str);
            printWriter.close();
            return true;
        }catch(IOException e){
            System.out.println("Адрес введен некорректно");
            return false;
        }
    }


    /**
     * this method runs the script from the specified file
     * @param collection this collection TreeSet from class Collection
     * @param FileName is a path to the file
     */
    @Override
    public boolean executeScript(CollectionOrganization collection, String FileName) { //execute_script C:/Users/Daniil/Desktop/Универ/Прога/lab5/File/start.json

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FileName));
            CollectionOrganization colOrg = collection;
            Work.goScriptWork(collection, this, reader);
            reader.close();
            return true;
        }catch(IOException e){
            System.out.println("Введен некорректный адрес");
            return false;
        }catch (NullPointerException e){
            System.out.println("Передано пустое значение");
            return false;
        }catch(StackOverflowError error){
            System.out.println("ну... тут мои полномочия все, память закончилась" +
                    "\n" +"введите нову команду");
            return false;
        }finally {
        }
    }


    /**
     * this method closes program
     */
    @Override
    public void exit() {
        System.exit(4221);
    }


    /**
     * this method adds a new element if it is less than the minimum
     * @param collection this collection TreeSet from class Collection
     * @param reader this parameter can implement both keyboard input and reading from a file
     * @param realization this parameter is enum Work.Realization. It parameter specifies how the work is performed, either interactively or using a script from a file
     */
    @Override
    public void addIfMin(CollectionOrganization collection, BufferedReader reader, Work.Realization realization) {
        Organization or = CreateElement.createElement(reader, realization);
        if(or.hashCode() < collection.getCollection().first().hashCode() && or != null ){
            collection.getCollection().add(or);
        }
    }

    /**
     * this method removes all elements if its greater than specified element
     * @param collection this collection TreeSet from class Collection
     * @param element this name the given element
     * @return return true if element was removed, and return false if element wasn't removed
     */
    @Override
    public boolean removeGreater(CollectionOrganization collection, String element) {
        Organization organization = null;
        for(Organization o: collection.getCollection()){
            if(o.getName().equals(element)){
                organization = o;
                break;
            }
        }

        if(organization == null){
            System.out.println("некорректное имя элемента");
            return false;
        }else{
            while(true){
                Organization o = collection.getCollection().higher(organization);
                if(o != null)
                    collection.getCollection().remove(o);
                else
                    return true;
            }
        }
    }


    /**
     * this method removes all elements if its less than specified element
     * @param collection this collection TreeSet from class Collection
     * @param element this name the given element
     * @return return true if element was removed, and return false if element wasn't removed
     */
    @Override
    public boolean removeLower(CollectionOrganization collection, String element) {
        Organization organization = null;
        for(Organization o: collection.getCollection()){
            if(o.getName().equals(element)){
                organization = o;
                break;
            }
        }

        if(organization == null){
            System.out.println("некорректное имя элемента");
            return false;
        }else{
            while(true){
                Organization o = collection.getCollection().lower(organization);
                if(o != null)
                    collection.getCollection().remove(o);
                else
                    return true;
            }
        }
    }


    /**
     * this method outputs number elements if its type greater specified
     * @param collection this collection TreeSet from class Collection
     * @param typeS is type from Enum OrganizationType
     * @see OrganizationType
     */
    @Override
    public void countGreaterThanType(CollectionOrganization collection, String typeS) {
        int count = 0;
        OrganizationType type = null;
        if(typeS.equalsIgnoreCase(OrganizationType.COMMERCIAL.toString())) {
            type = OrganizationType.COMMERCIAL;
        }else if(typeS.equalsIgnoreCase(OrganizationType.PUBLIC.toString())){
            type = OrganizationType.PUBLIC;
        }else if(typeS.equalsIgnoreCase(OrganizationType.TRUST.toString())){
            type = OrganizationType.TRUST;
        }else{
            System.out.println("Введен некорректный тип");
        }

        for (Organization o: collection.getCollection()){
            if(o.getType().VALUE > type.VALUE)
                count++;
        }
        if(count >= 0)
            System.out.println("колличество элементов ID которых больше "  + typeS + ": " + count);
    }


    /**
     * this method outputs collection in reverse order
     * @param collection this collection TreeSet from class Collection
     */
    @Override
    public void printDescending(CollectionOrganization collection) {
        TreeSet<Organization> deOr = (TreeSet<Organization>) collection.getCollection().descendingSet();
        for (Organization o: deOr){
            System.out.println(o.getName());
        }
    }


    /**
     * this method outputs values parameter Address in ascending order
     * @param collection this collection TreeSet from class Collection
     */
    @Override
    public void printFieldAscendingOfficialAddress(CollectionOrganization collection) {
        TreeSet<Address> treeAddress = new TreeSet<Address>(new Comparator<Address>() {
            @Override
            public int compare(Address o1, Address o2) {
                return o1.getZipCode().compareTo(o2.toString());
            }
        });
        for (Organization o: collection.getCollection())
            treeAddress.add(o.getOfficialAddress());

        for(Address s: treeAddress)
            System.out.println(s);
    }
}
