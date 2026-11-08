import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Statistics {


    public ArrayList<Double> count(ArrayList<Double[]> X) {

        ArrayList<Double> counts = new ArrayList<>();
        double counter = 0;

        for(int i = 0 ; i < X.getFirst().length ; i++){
            for(int j = 0 ; j < X.size(); j++){
                if(X.get(j)[i] != 0){
                    counter++;
                }
            }
            counts.add(counter);
            counter = 0;
        }


        System.out.print("count || ");
        for(Double count : counts){

            System.out.print(count + " | ");
        }


        return counts;
    }

    public ArrayList<Double> mean(ArrayList<Double[]> X ) {
        ArrayList<Double> means = new ArrayList<>();
        double sum = 0;
        for(int i = 0 ; i < X.getFirst().length ; i++){
            for(int j = 0 ; j < X.size(); j++){
                sum += X.get(j)[i];
            }
            means.add(sum / X.size());
            sum = 0;
        }

        System.out.print("mean || ");
        for(Double k : means){

            System.out.print(k + " | ");
        }

        return means;
    }

    public ArrayList<Double> std(ArrayList<Double[]> X , ArrayList<Double> means) {

        int n = X.size();

        ArrayList<Double> stds = new ArrayList<>();

        for(int i = 0 ; i < X.getFirst().length ; i++){
            double std = 0;
            double result = 0;
            for(int j = 0 ; j < X.size() ; j++){
                std += Math.pow(X.get(j)[i] - means.get(i) , 2);
                result = Math.sqrt(std / (n - 1));
            }
            stds.add(result);

        }

        System.out.print("std || ");
        for(Double element : stds){
            System.out.print(element + " | ");
        }
        return stds;
    }

    public ArrayList<Double> min(ArrayList<Double[]> X){
        double minElement = 0;
        ArrayList<Double> minElements = new ArrayList<>();
        for(int i = 0 ; i < X.getFirst().length ; i++){
             minElement = X.get(i)[0];
            for(int j = 0 ; j < X.size() ; j++){
                if(minElement > X.get(j)[i]){
                    minElement = X.get(j)[i];
                }
            }
            minElements.add(minElement);


        }
        System.out.print("min || ");
        for(Double element : minElements){
            System.out.print(element + " | ");
        }



        return minElements;
    }

    public ArrayList<Double> max(ArrayList<Double[]> X){
        double maxElement = 0;
        ArrayList<Double> maxElements = new ArrayList<>();
        for(int i = 0 ; i < X.getFirst().length ; i++){
            maxElement = X.get(i)[0];
            for(int j = 0 ; j < X.size() ; j++){
                if(maxElement < X.get(j)[i]){
                    maxElement = X.get(j)[i];
                }
            }
            maxElements.add(maxElement);


        }
        System.out.print("max || ");
        for(Double element : maxElements){
            System.out.print(element + " | ");
        }



        return maxElements;
    }

    public double quarterPercent(ArrayList<Double[]> X){

        ArrayList<Double> quartersPercents = new ArrayList<>();

        for(int i = 0 ; i < X.getFirst().length ; i++){

            for(int j = 0 ; j < X.size(); j++){

            }


        }

        return 0.0;
    }

    public double halfPercent(){
        return 0.0;
    }
    public double threeQuartersPercent(){
        return 0.0;
    }


}