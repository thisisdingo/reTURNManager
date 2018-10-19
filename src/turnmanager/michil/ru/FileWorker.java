package turnmanager.michil.ru;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWorker {
    private static FileWorker ourInstance = new FileWorker();

    public static FileWorker getInstance() {
        return ourInstance;
    }

    private FileWorker() {
    }

    public String getListReTurnUsersList() throws Exception {
        return new String(Files.readAllBytes(Paths.get(Constants.DEFAULT_CONFIG_ADDRESS)));
    }

    public void writeToUsersDisk(String newUsers) throws Exception {
        File myFoo = new File(Constants.DEFAULT_CONFIG_ADDRESS);
        FileWriter fooWriter = new FileWriter(myFoo, false);
        fooWriter.write(newUsers);
        fooWriter.close();
    }
}
