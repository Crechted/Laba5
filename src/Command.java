import java.io.BufferedReader;

/**
 * this interface has entire list of commands available to the User
 */
public interface Command {
    /**
     * this method has a default implementation. It output entire list of commands
     */
    default void help(){
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add: добавить новый элемент в коллекцию\n" +
                "update_id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "save_as {File_name}: сохранить коллекцию в указанный файл(путь должен содержать так же и название файла типа json)\n" +
                "execute_script {file_name} : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "count_greater_than_type type : вывести количество элементов, значение поля type которых больше заданного\n" +
                "print_descending : вывести элементы коллекции в порядке убывания\n" +
                "print_field_ascending_official_address: вывести значения поля officialAddress в порядке возрастания");
    }  //вывести справку по доступным командам;

    void info(CollectionOrganization collection);  //вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)

    void show(CollectionOrganization collection);   //вывести в стандартный поток вывода все элементы коллекции в строковом представлении

    void add(CollectionOrganization collection, BufferedReader reader, Work.Realization realization);  //добавить новый элемент в коллекцию

    void updateElement(CollectionOrganization collection, String element);   //обновить значение элемента коллекции, id которого равен заданному

    boolean remove(CollectionOrganization collection, long id);    //удалить элемент из коллекции по его id

    void clear(CollectionOrganization collection);   //очистить коллекцию

    boolean save(CollectionOrganization collection);   //сохранить коллекцию в файл

    boolean saveAs(CollectionOrganization collection,String FileName);   //сохранить коллекцию в указанный (новый) файл

    boolean executeScript(CollectionOrganization collection, String FileName); //считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.

    void exit();   //завершить программу (без сохранения в файл)

    void addIfMin(CollectionOrganization collection,BufferedReader reader, Work.Realization realization);//добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции

    boolean removeGreater(CollectionOrganization collection,String element);  //удалить из коллекции все элементы, превышающие заданный

    boolean removeLower(CollectionOrganization collection,String element);  //удалить из коллекции все элементы, меньшие, чем заданный

    void countGreaterThanType(CollectionOrganization collection,String type);   //вывести количество элементов, значение поля type которых больше заданного

    void printDescending(CollectionOrganization collection);  //вывести элементы коллекции в порядке убывания

    void printFieldAscendingOfficialAddress (CollectionOrganization collection);  //вывести значения поля officialAddress в порядке возрастания
}
