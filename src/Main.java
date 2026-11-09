import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//        double[][] x = {
//                {3.0, 0.10, 0, 30},
//                {4.0, 0.20, 1, 40},
//                {5.0, 0.15, 0, 20}
//        };
//
//        double[] y = {150, 200, 250};
//
//
//        System.out.print("number of houses: ");
//        System.out.println(y.length);
//        System.out.print("Number of features: ");
//        System.out.println(x.length);
//
//        for(int i = 0 ; i < 3 ; i++){
//            System.out.println(x[1][i]);
//        }


        String filePath = "/home/nano/Desktop/Programing/java/linearRegressionFromScratch/LinearRegressionFromScratch/data/boston.csv";

        final String valueDelimiter = "\\s+";
        ArrayList<Double[]> X = new ArrayList<>();
        ArrayList<Double> Y = new ArrayList<>();
        int i = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while((line = reader.readLine()) != null){
                line = line.replace("\"" , "");

                // skip the header text and blank lines: data lines contain only digits, '.', '-' and spaces
                if (!line.matches("[\\d.\\s-]+")) continue;

                String[] parts = line.trim().split(valueDelimiter);

                if(i %2 == 0){

                    ArrayList<Double> row = new ArrayList<>();
                    for (String part : parts) {
                        row.add(Double.parseDouble(part));
                    }
                    X.add(row.toArray(new Double[0]));

                }
                else{

                    Y.add(Double.parseDouble(parts[0]));
                }
                i++;


            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went Wrong!");
        }

        for(Double y : Y){
            System.out.print(y);
        }
        System.out.println();

        System.out.println("CRIM   " +
                "    ZN     " +
                "INDUS  " +
                " CHAS   " +
                " NOX    " +
                " RM     " +
                "  AGE    " +
                " DIS    " +
                "  RAD    " +
                "TAX    " +
                "PTRATIO" +
                "    B      " +
                "LSTAT  ");
        for(Double[] x : X){
            for (Double element : x){
                System.out.print(element + "    ");
            }
            System.out.println();
        }
        for (Double y : Y){
            System.out.print(y + "    ");
        }
        System.out.println();


        System.out.println("Number of houses: " + X.size());
        System.out.println("Number of targets: " + Y.size());
        System.out.println("Features per house: " + X.getFirst().length);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //statistics
        Statistics statistics = new Statistics();

        ArrayList<Double> counts = statistics.count(X, Y);
        System.out.println();
        ArrayList<Double> stds = statistics.mean( X, Y);
        System.out.println();
        statistics.std(X , stds, Y);
        System.out.println();
        statistics.min(X , Y);
        System.out.println();
        statistics.max(X , Y);
        System.out.println();
        statistics.quarterPercent(X, Y);
        System.out.println();
        statistics.halfPercent(X, Y);
        System.out.println();
        statistics.threeQuartersPercent(X, Y);






    }


}