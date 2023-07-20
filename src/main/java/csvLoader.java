public class csvLoader {
    public static void main(String[] main){

        // table: <name of the table created in mysql phpadmin>
        csvLoadData users = new csvLoadData("users");
        users.loadData();

        csvLoadData roles = new csvLoadData("roles");
        roles.loadData();
        System.out.print("Code done!");

    }
}


