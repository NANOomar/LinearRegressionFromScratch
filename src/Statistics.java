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

    public double std() {
        return 0.0;
    }

    public double min(){
        return 0.0;
    }

    public double quarterPercent(){
        return 0.0;
    }

    public double halfPercent(){
        return 0.0;
    }
    public double threeQuartersPercent(){
        return 0.0;
    }


}