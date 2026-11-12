import java.util.ArrayList;
import java.util.Random;

public class LinearRegression extends FeatureScaling{

    private ArrayList<Double> theta = new ArrayList<>();
    private ArrayList<Double> hypothesisValues = new ArrayList<>();
    private ArrayList<ArrayList<Double>> X_Train = getTrainStandardizedValues();
    private ArrayList<Double> Y_Train = getY_train();
    private ArrayList<Double> gradientVector = new ArrayList<>();
    private static double costFunctionValue;
    private static double learningRate = 0.3;
    boolean isTheta = false;


    public void hypothesis(ArrayList<ArrayList<Double>> X){
        hypothesisValues.clear();
        if(isTheta == false){
            initializeTheta();
        }

        int numHouses = X.size();
        int numFeatures = X.getFirst().size();

        for(int i = 0 ; i < numHouses ; i++){
            double sum = theta.getFirst();
            for(int j = 0 ; j < numFeatures ; j++){
                sum += theta.get(j + 1) * X.get(i).get(j);

            }
            hypothesisValues.add(sum);
        }

//        System.out.println();
//        System.out.print(hypothesisValues.size());
//        System.out.print("hypothesisValues || ");
//        for(Double element : hypothesisValues){
//            System.out.print(element + " | ");
//        }
//        System.out.println();
//        System.out.print(Y_Train.size());
//        System.out.print("train || ");
//        for(Double element : Y_Train){
//            System.out.print(element + " | ");
//        }
//        System.out.println();
//        System.out.println(costFunction());
    }

    private void initializeTheta(){
        if (X_Train != null) {
            theta.clear();

            for(int d = 0 ; d < X_Train.getFirst().size() + 1 ; d++){
                theta.add(0.0);
            }
            isTheta = true;
        }
    }

    public double costFunction(ArrayList<Double> Y){
        double sum = 0;
        double result = 0;
        int numHouses = hypothesisValues.size();
        for(int i = 0; i < numHouses ; i++){
            sum += Math.pow((hypothesisValues.get(i) - Y.get(i)) , 2);
        }
        result =  sum  / (2 * numHouses);

        costFunctionValue = result;
        return result;
    }

    public void calculateGradient(){
        gradientVector.clear();

        int numHouses = X_Train.size();
        int numFeatures = X_Train.getFirst().size();
        boolean biasX = false;
        for(int i = 0 ; i < numFeatures  ; i++){
            double sum = 0;
            double sumBias = 0;
            for(int j = 0 ; j < numHouses  ; j++){
                if(biasX == false){
                    sumBias += hypothesisValues.get(j) - Y_Train.get(j);
                }

                sum += (hypothesisValues.get(j) - Y_Train.get(j)) * X_Train.get(j).get(i);


            }
            if(biasX == false){
                gradientVector.add(sumBias/numHouses);
            }

            biasX = true;
            gradientVector.add(sum/numHouses);
        }



//        System.out.println();
//        System.out.println(gradientVector.size());
//
//        for(Double element : gradientVector) {
//            System.out.println(element);
//        }
//
//        soutMean();

    }

    public void updateTheta(){

        for(int i = 0 ; i < theta.size() ; i++){
            double result = 0;
            result = theta.get(i) - (learningRate * gradientVector.get(i));
            theta.set(i , result);
        }


    }

    public void gradientDescent(){

        int iterations = 1000;
        System.out.println("learning rate : " + learningRate);

        for(int i = 0 ; i < iterations ; i++){

            hypothesis(X_Train);

            calculateGradient();

            updateTheta();


            if(i % 100 == 0) {

                //hypothesis();
                System.out.println("itr: " + i + " | " + costFunction(Y_Train));
            }
        }



    }

    public void predict(){

        ArrayList<ArrayList<Double>> test_data = new ArrayList<>();
        Random random = new Random();
        int randomIndex = 0;


        System.out.print("\nrea values || ");
        for(int i = 0 ; i < getTestStandarazedValues().size() ; i++){
//            randomIndex = random.nextInt(0 , getTestStandarazedValues().size() - 1);
//            test_data.add(getTestStandarazedValues().get(randomIndex));
            test_data.add(getTestStandarazedValues().get(i));

            System.out.print(getY_test().get(i) + " | ");
        }

        hypothesis(test_data);
        System.out.println();
        System.out.print(hypothesisValues.size());
        System.out.print("hypothesisValues || ");
        for(Double element : hypothesisValues){
            System.out.print(element + " | ");
        }

        double RMSE = Math.sqrt(2 * costFunction(getY_test()));
        System.out.println("\nRMSE : " + RMSE );



    }





}
