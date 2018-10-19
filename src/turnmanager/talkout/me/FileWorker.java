package turnmanager.talkout.me;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static turnmanager.talkout.me.Constants.DEFAULT_CONFIG_ADDRESS;

public class FileWorker {
    private static FileWorker ourInstance = new FileWorker();

    public static FileWorker getInstance() {
        return ourInstance;
    }

    private FileWorker() {
    }

    public String getListReTurnUsersList() throws Exception {
        return new String(Files.readAllBytes(Paths.get(DEFAULT_CONFIG_ADDRESS)));
    }

    public void writeToUsersDisk(String newUsers) throws Exception {
        File myFoo = new File(DEFAULT_CONFIG_ADDRESS);
        FileWriter fooWriter = new FileWriter(myFoo, false);
        fooWriter.write(newUsers);
        fooWriter.close();
    }
}
