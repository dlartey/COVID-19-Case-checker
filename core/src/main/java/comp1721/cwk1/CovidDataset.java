package comp1721.cwk1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class CovidDataset {
    private LinkedList<CaseRecord> records;

    public CovidDataset(){
        records = new LinkedList<>();
    }
    //TODO: Write stub for size()

    public int size(){
        return records.size();
    }

    //TODO: Write stub for getRecord()
    public CaseRecord getRecord(int index){
        try{
            return records.get(index);
        }catch (Exception e){
            throw new DatasetException("Enter a valid integer");
        }
    }
    // TODO: Write stub for addRecord()

    public void addRecord(CaseRecord rec){
        records.add(rec);
    }

    // TODO: Write stub for dailyCasesOn()
    public CaseRecord dailyCasesOn(LocalDate day){
        boolean check = false;
        int index = 0;
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getDate() == day){
                check = true;
                index = i;
            }
        }

        if (check){
            return records.get(index);
        }else{
            throw new DatasetException("Date not found!");
        }
    }

    // TODO: Write stub for readDailyCases()

    public void readDailyCases(String filename) throws IOException {
        int count = 0;
        try{
            records.clear();
            File file = new File(filename);
            if (! file.exists()){
                throw new FileNotFoundException("Could not find file: "+filename);
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                int count2= 0;
                if (count ==0){
                    count = 1;
                }else{
                    String[] split = line.split(",");
                    for (int i = 0; i < split.length; i++){
                        if (i == split.length-1 && count2!=3){
                            throw new DatasetException("Not enough columns");
                        }else if (i == split.length-1 && count2 == 3){
                            LocalDate d = LocalDate.parse(split[0]);
                            int staffC = Integer.parseInt(split[1]);
                            int studentC = Integer.parseInt(split[2]);
                            int otherC = Integer.parseInt(split[3]);
                            CaseRecord cases = new CaseRecord(d,staffC,studentC,otherC);
                            records.add(cases);
                        }
                        count2+=1;
                    }
                }

            }
        } finally{
            count = 1;
        }

    }

    // TODO: Write stub for writeActiveCases()

    public void writeActiveCases(String filename) throws IOException{
            if (records.size() < 10){
                throw new DatasetException("Must be at least 10 days");
            }else{
                FileWriter writer = new FileWriter(filename,false);
                writer.write("Date,Staff,Students,Other\n");

                for (int i = 9; i < records.size(); i++){
                    int count1 = i-1;
                        int activeCaseStudent = 0;
                        int activeCaseStaff = 0;
                        int activeCaseOther = 0;
                            for (int a = i-9; a<=i; a++){
                                activeCaseStudent = activeCaseStudent +
                                        records.get(a).getStudentCases();
                                activeCaseStaff = activeCaseStaff +records.get(a).getStaffCases();
                                activeCaseOther = activeCaseOther + records.get(a).getOtherCases();
                            }
                            writer.write(records.get(i).getDate()+","+activeCaseStaff+","+
                                    activeCaseStudent+","+activeCaseOther+"\n");
                }
                writer.close();
            }
        }


}
