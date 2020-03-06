import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.TreeSet;


/**
 * this class starts the program
 * @see CollectionOrganization
 * @see Organization
 * @see Command
 * @see Work
 * @see CommandImp1
 * @see F
 */
public class Lab5 {

    public final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static String address = null;
    public static String getArgs(){
        return address;
    }
    public static void setArgs(String s){
        address = s;
    }

    /**
     *Creates a new object of the CollectionOrganization class.
     *Created collection with objects from the file whose address is passed in the args variable[0]
     * @param args address of thew file
     * @see CollectionOrganization
     * @see Work
     */
    public static void main(String[] args) {
        try {
            setArgs(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("нет переменных окружения");
        }
        CollectionOrganization collectionOrganization = new CollectionOrganization();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(getArgs())));
            String str = "", s = null;
            while ((s = reader.readLine()) != null){
                str += "\n"+s;
            }
            
            collectionOrganization = GSON.fromJson(str, CollectionOrganization.class);

        } catch (FileNotFoundException ex) {

        } catch (EOFException e) {

        } catch(NullPointerException e){

        } catch (IOException e){
            System.out.println("Файл не найден");
        } catch (Exception e){
            System.out.println("не подходящий файл для чтения");
        }
        Work.goInteractiveWork(collectionOrganization, new CommandImp1());

    }// C:\Programming\Plugin\gson-2.8.6.jar Lab5 C:/Programming/resultNJ.json
}