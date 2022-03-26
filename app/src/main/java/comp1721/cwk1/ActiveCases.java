package comp1721.cwk1;

import java.io.IOException;

public class ActiveCases {


    public static void main(String[] args) throws IOException {
        CovidDataset test = new CovidDataset();
        String readFile = "";
        String writeFile = "";
        if (args.length !=2){
            System.out.println("Invalid number of arguments");
            System.exit(0);
        }else{
            readFile = args[0];
            writeFile = args[1];
        }

        try{
            test.readDailyCases(readFile);
            test.writeActiveCases(writeFile);
            System.out.println(test.size());
        }catch (IOException e){
            System.out.println("Unable to read/write to files!");
            System.exit(2);
        }


    }


}
