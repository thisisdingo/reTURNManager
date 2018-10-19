package turnmanager.talkout.me;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        Options options = new Options();

        Option input = new Option("s", "secretKey", true, "Secret key for access to REST API");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("p", "path", true, "reTURN users.txt file location");
        output.setRequired(false);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String secretKey = cmd.getOptionValue("secretKey");
            String dbPath = cmd.getOptionValue("path");

            if (dbPath != null){
                Constants.DEFAULT_CONFIG_ADDRESS = dbPath;
            }
            Constants.SECRET_KEY = secretKey;


            new TurnManager();

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Turn Server Manager", options);

            System.exit(1);
        }




    }
}
