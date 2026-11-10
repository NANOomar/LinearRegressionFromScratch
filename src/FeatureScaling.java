import java.util.ArrayList;

public class FeatureScaling {

    private static boolean runOnceMean = false;
    private static boolean runOnceStd = false;
    private static boolean runOnceTT = false;
    private static boolean runOnceT = false;
    private static ArrayList<Double> meanValues = new ArrayList<>();
    private static ArrayList<Double> stdValues = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> trainStandarazedValues = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> testStandarazedValues = new ArrayList<>();

    public void meanX(ArrayList<ArrayList<Double>> X_Train){
        if(runOnceMean == true) return;

        int numHouses = X_Train.size();
        int numFeatures = X_Train.getFirst().size();
        for(int i = 0 ; i <  numFeatures; i++){
            double sum = 0;
            for(int j = 0 ; j <  numHouses; j++){
                sum += X_Train.get(j).get(i);
            }
            meanValues.add(sum / numHouses);
        }

        System.out.println();
        System.out.print("mean || ");
        for(Double element : meanValues){
            System.out.print(element + " | ");
        }

        runOnceMean = true;

    }

    public void stdX(ArrayList<ArrayList<Double>> X_Train){

        if(runOnceStd == true) return;

        int numHouses = X_Train.size();
        int numFeatures = X_Train.getFirst().size();
        for(int i = 0 ; i <  numFeatures; i++){
            double sum = 0;
            for(int j = 0 ; j <  numHouses; j++){
                sum += Math.pow(X_Train.get(j).get(i) - meanValues.get(i) , 2);
            }

            double result = Math.sqrt(sum / X_Train.size());
            stdValues.add(result);
        }

        System.out.println();
        System.out.print("std || ");
        for(Double element : stdValues){
            System.out.print(element + " | ");
        }

        runOnceStd = true;
    }

    public void standardization(ArrayList<ArrayList<Double>> X_Train){



            meanX(X_Train);
            stdX(X_Train);


        if(runOnceT == false){

            int nubHouses = X_Train.size();
            int numFeatures = X_Train.getFirst().size();

            for(int i = 0 ; i < nubHouses ; i++){
                ArrayList<Double> row = new ArrayList<>();

                for(int j = 0 ; j < numFeatures ; j++){
                    double result = (X_Train.get(i).get(j) - meanValues.get(j)) / stdValues.get(j);
                    row.add(result);
                }
                trainStandarazedValues.add(row);
            }

            runOnceT = true;

        }


        System.out.println("//////////////////////////////////////////////////////////////////////////////");
        System.out.print("standardized || ");
        for(ArrayList<Double> elements : trainStandarazedValues){
            for(Double element : elements){
                System.out.print(element + " | ");
            }
            System.out.println();
        }



    }
    public void standardization(ArrayList<ArrayList<Double>> X_test , ArrayList<ArrayList<Double>> X_Train){




            meanX(X_Train);
            stdX(X_Train);

        if(runOnceTT == false){
            int nubHouses = X_test.size();
            int numFeatures = X_test.getFirst().size();

            for(int i = 0 ; i < nubHouses ; i++){
                ArrayList<Double> row = new ArrayList<>();

                for(int j = 0 ; j < numFeatures ; j++){
                    double result = (X_test.get(i).get(j) - meanValues.get(j)) / stdValues.get(j);
                    row.add(result);
                }
                testStandarazedValues.add(row);
            }

            runOnceTT = true;
        }






        System.out.println("//////////////////////////////////////////////////////////////////////////////");
        System.out.print("standardized || ");
        for(ArrayList<Double> elements : testStandarazedValues){
            for(Double element : elements){
                System.out.print(element + " | ");
            }
            System.out.println();
        }



    }
}
