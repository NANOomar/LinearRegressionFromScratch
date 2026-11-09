import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Correlation {

    private ArrayList<Double> correlationsAbsSorted = new ArrayList<>();
    private ArrayList<Double[]> x_train = new ArrayList<>();
    private ArrayList<Double> y_train = new ArrayList<>();
    private ArrayList<Double[]> x_test = new ArrayList<>();
    private ArrayList<Double> y_test = new ArrayList<>();

    public ArrayList<Double> calculateCorrelation(ArrayList<Double[]> X , ArrayList<Double> Y){

        ArrayList<Double> correlations = new ArrayList<>();
        Statistics statistics = new Statistics();
        ArrayList<Double> means= statistics.mean(X, Y);
        double meanY = means.getLast();
        int n = X.size();
        int featuresNum = X.getFirst().length;





        for(int i = 0 ; i < featuresNum ; i++){
            double sumxxyy = 0;
            double sumxx = 0;
            double sumyy = 0;
            double result = 0;
            ArrayList<Double> features = statistics.row(X , i , false);
            double meanX = means.get(i);
            for(int j = 0 ; j < n ; j++){

                sumxxyy += (features.get(j) - meanX) * (Y.get(j) - meanY);
                sumxx += Math.pow( (features.get(j) - meanX ), 2) ;
                sumyy += Math.pow((Y.get(j) - meanY) , 2);
            }
            correlations.add(sumxxyy / (Math.sqrt(sumxx * sumyy)));

        }

//        System.out.print("correlations || ");
//        for(Double element : correlations){
//            System.out.print(element + " | ");
//        }



        return correlations;
    }



    public ArrayList<Integer> featuresSelection(ArrayList<Double[]> X , ArrayList<Double> Y){


        ArrayList<Integer> indexFeatures = new ArrayList<>();
        int index = 0;

        for(Double element : calculateCorrelation(X , Y)){

            if(Math.abs(element) > 0.3){

                correlationsAbsSorted.add(Math.abs(element));
            }
            indexFeatures.add(index);

            index++;

        }

        Collections.sort(correlationsAbsSorted);
//        System.out.print("correlationsAbsSorted || ");
//        for(Double element : correlationsAbsSorted){
//            System.out.print(element + " | ");
//        }




        return indexFeatures;
    }


    public void splitData(ArrayList<Double[]> X , ArrayList<Double> Y) {

        Random random = new Random(42);

        Collections.shuffle(X, random);
        Collections.shuffle(Y, random);

        int num_features = X.getFirst().length;
        int n = X.size();
        int trainLength = (int) Math.round(X.size() * 0.8);
        int testLength = n - trainLength;
        int i = 0;

        for (int j = 0; j < featuresSelection(X, Y).size(); j++) {
            int element = featuresSelection(X, Y).get(i);
            for (int k = 0; k < X.size(); k++) {
                X.remove(X.get(k)[element]);
            }
        }

        for (i = 0; i < (trainLength + testLength); i++) {
            if (i >= trainLength) {
                x_test.add(X.get(i));
                y_test.add(Y.get(i));
            } else {
                x_train.add(X.get(i));
                y_train.add(Y.get(i));
            }
        }

        System.out.print("x_train || ");
        for(Double[] x : x_train){
            for(Double element : x){
                System.out.print(element + " | ");
            }
        }

        System.out.println();

        System.out.print("y_train || ");
        for(Double element : y_train){
            System.out.print(element + " | ");
        }

        System.out.println();

        System.out.print("x_test || ");
        for(Double[] x : x_test){
            for(Double element : x){
                System.out.print(element + " | ");
            }
        }

        System.out.println();

        System.out.print("y_test || ");
        for(Double element : y_test){
            System.out.print(element + " | ");
        }



    }

}
