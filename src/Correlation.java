import java.util.ArrayList;

public class Correlation {

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

        System.out.print("correlations || ");
        for(Double element : correlations){
            System.out.print(element + " | ");
        }



        return correlations;
    }


}
