
import com.sun.security.jgss.GSSUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Correlation {

    private ArrayList<Double> correlationsAbsSorted = new ArrayList<>();
    private ArrayList<ArrayList<Double>> x_train = new ArrayList<>();
    private ArrayList<Double> y_train = new ArrayList<>();
    private ArrayList<ArrayList<Double>> x_test = new ArrayList<>();
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
        double rate = 0.7;
        int index = 0;

        for(Double element : calculateCorrelation(X , Y)){

            if(Math.abs(element) > rate){

                correlationsAbsSorted.add(Math.abs(element));
            }
            else{
                indexFeatures.add(index);
            }


            index++;

        }

        Collections.sort(correlationsAbsSorted);
        System.out.print("correlationsAbsSorted || ");
        for(Double element : correlationsAbsSorted){
            System.out.print(element + " | ");
        }




        return indexFeatures;
    }


    public void splitData(ArrayList<Double[]> X , ArrayList<Double> Y) {

        ArrayList<ArrayList<Double>> X_modified = new ArrayList<>();
        ArrayList<ArrayList<Double>> X_shuffled = new ArrayList<>();
        ArrayList<Double> Y_shuffled = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();

        Random random = new Random(42);


        for(int i = 0 ; i < X.size() ; i++){
            indexes.add(i);
        }
        Collections.shuffle(indexes , random);





        for(Double[] row : X){
            X_modified.add(new ArrayList<>(Arrays.asList(row)));
        }




        int n = X.size();
        int trainLength = (int) Math.round(X.size() * 0.8);
        int testLength = n - trainLength;
        int i = 0;


        for(int index : indexes){
            X_shuffled.add(X_modified.get(index));
            Y_shuffled.add(Y.get(index));
        }

        for (i = 0; i < (trainLength + testLength); i++) {
            if (i < trainLength) {
                x_train.add(X_shuffled.get(i));
                y_train.add(Y_shuffled.get(i));
            } else {
                x_test.add(X_shuffled.get(i));
                y_test.add(Y_shuffled.get(i));
            }
        }




        ArrayList<Double[]> trainRaw = new ArrayList<>();
        for (int k = 0; k < x_train.size(); k++) {
            trainRaw.add(x_train.get(k).toArray(new Double[0]));
        }

        // feature selection uses train only (no test data involved)
        ArrayList<Integer> toRemove = featuresSelection(trainRaw, y_train);
        Collections.sort(toRemove, Collections.reverseOrder());

        // remove the SAME columns from train and test
        for (int k = 0; k < x_train.size(); k++) {
            for (int j = 0; j < toRemove.size(); j++) {
                x_train.get(k).remove((int) toRemove.get(j));
            }
        }
        for (int k = 0; k < x_test.size(); k++) {
            for (int j = 0; j < toRemove.size(); j++) {
                x_test.get(k).remove((int) toRemove.get(j));
            }
        }








        System.out.println("size x:" + x_train.size());

        System.out.print("x_train || ");
        int z = 0;
        for(ArrayList<Double> x : x_train){
            for(Double element : x){
                System.out.print(element + " | ");
                z++;
            }

            System.out.println();
        }

        System.out.println(z);
        System.out.println();

        System.out.print("y_train || ");
        for(Double element : y_train){
            System.out.print(element + " | ");
        }

        System.out.println();

        System.out.print("x_test || ");
        for(ArrayList<Double> x : x_test){
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
