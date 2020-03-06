import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * this class implements work in interactive mode as well as reading from a file
 */
public class Work {
    private static String stri;
    private static BufferedReader reader;

    /**
     * this enum has two type work: InteractiveWork, ScriptWork
     */
    public enum Realization{
        InteractiveWork,
        ScriptWork;
    }

    /**
     * this method runs InteractiveWork
     * @param collectionOrganization this collection TreeSet from class Collection
     * @param commandImp this instance class Command
     */
    public static void goInteractiveWork(CollectionOrganization collectionOrganization, Command commandImp){
        System.out.println("Здравствуйте, была создана коллекция с элементами типа Organization." +
                "\n" + "Элементы были созданы на основе файла " + Lab5.getArgs());
        int num = collectionOrganization.getCollection().size();
        String s = "эл.";
        switch (num % 10){
            case 1:{ s = "элемент";
            break;}
            case 2:
            case 3:
                case 4:{ s = "элемента";
                    break;}
            case 0:
            case 5:
            case 6:
            case 7:
            case 8:
                case 9:{ s = "элементов";
            break;}
        }
        System.out.println("В данный момент в коллекции " + num + " " + s +
                "\n" + "Вам доступны следующие команды");
        commandImp.help();
        System.out.println("Введите первую команду: ");
        while(true) {
            try {
                reader = new BufferedReader (new InputStreamReader(System.in));
                boolean b =goCommand(collectionOrganization, commandImp, reader, Realization.InteractiveWork);
                if(b == false){
                    System.out.println("команда введена некорректно, попробуйте снова:");
                }else{
                    System.out.println("команда успешно выполнена!");
                    System.out.println("Введите новую команду:");
                }
                //reader.close();
            } catch (IOException e) {
                System.out.println("исключение *происходится* . Попробуйте снова:");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("исключение *происходится* . Попробуйте снова:");
            } catch (Exception e){
                System.out.println("исключение *происходится* . Попробуйте снова:");
            }
        }
    }

    /**
     * this method runs ScriptWork
     * @param collectionOrganization this collection TreeSet from class Collection
     * @param commandImp this instance class Command
     * @param reader this parameter can implement both keyboard input and reading from a file
     */
    public static void goScriptWork(CollectionOrganization collectionOrganization, Command commandImp, BufferedReader reader){
        for(int i = 1; true; i++) {
            try {
                boolean b = goCommand(collectionOrganization, commandImp, reader, Realization.ScriptWork);
                if (stri == null)
                    break;
                if (b == false){
                    System.out.println("ошибка в " + i + " строке файла");
                }
            } catch (IOException e) {

            } catch (StringIndexOutOfBoundsException e) {

            } catch (Exception e) {

            }
        }
    }

    /**
     * /**
     * this method calls the required method from the class implements Command
     * @param collectionOrganization this collection TreeSet from class Collection
     * @param commandImp this instance class Command
     * @param reader this parameter can implement both keyboard input and reading from a file
     * @param realization this parameter is enum Work.Realization. It parameter specifies how the work is performed, either interactively or using a script from a file
     * @return return if command completed and return false if was a problem
     * @throws IOException this exception is handled higher up in the stack
     * @throws StringIndexOutOfBoundsException this exception is handled higher up in the stack
     */
    private static boolean goCommand(CollectionOrganization collectionOrganization, Command commandImp, BufferedReader reader, Work.Realization realization) throws IOException, StringIndexOutOfBoundsException{

        String str = reader.readLine();
        stri = str;
        if(str == null){
            Work.reader = new BufferedReader(new InputStreamReader(System.in));
            return false;
        }else if(str.equals("")){
            return false;
        }else if(str.equalsIgnoreCase("f")){
            try {
                F.getF();
                return true;
            }catch (InterruptedException e){
                return true;
            }
        }else if(str.equals("help")){
            commandImp.help();
            return true;
        }else if(str.equals("info")){
            commandImp.info(collectionOrganization);
            return true;
        }else if(str.equals("show")){
            commandImp.show(collectionOrganization);
            return true;
        }else if(str.equals("clear")){
            commandImp.clear(collectionOrganization);
            return true;
        }else if(str.equals("save")){
            return commandImp.save(collectionOrganization);
        }else if(str.equals("exit")){
            System.out.println("тут мои полномочия, все...");
            commandImp.exit();
            return true;
        }else if(str.equals("print_descending")){
            commandImp.printDescending(collectionOrganization);
            return true;
        }else if(str.equals("add")){
            commandImp.add(collectionOrganization, reader, realization);
            return true;
        }else if(str.substring(0, 7).equals("save_as")){
            return commandImp.saveAs(collectionOrganization, str.substring(8));
        }else if(str.substring(0, 9).equals("update_id")){
            commandImp.updateElement(collectionOrganization, str.substring(10));
            return true;
        }else if(str.substring(0,12).equals("remove_by_id")){
            return commandImp.remove(collectionOrganization, Long.parseLong(str.substring(13)));
        }else if(str.substring(0, 14).equals("execute_script")){
            return commandImp.executeScript(collectionOrganization, str.substring(15));
        }else if(str.substring(0, 10).equals("add_if_min")){
            commandImp.addIfMin(collectionOrganization, reader, realization);
            return true;
        }else if(str.substring(0, 14).equals("remove_greater")){
            return commandImp.removeGreater(collectionOrganization, str.substring(15));
        }else if(str.substring(0, 12).equals("remove_lower")){
            return commandImp.removeLower(collectionOrganization, str.substring(13));
        }else if(str.substring(0, 23).equals("count_greater_than_type")){
            commandImp.countGreaterThanType(collectionOrganization, str.substring(24));
            return true;
        }else if(str.substring(0, 38).equals("print_field_ascending_official_address")){
            commandImp.printFieldAscendingOfficialAddress(collectionOrganization);
            return true;
        }else {
            return false;
        }
    }
}
