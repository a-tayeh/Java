public class AlgorithmAnalysisLab {
    public static void main(String [] args){


        bigOh();


    }

    public static void bigOh(){
        long start = System.nanoTime();
        fragNum8();
        long end = System.nanoTime();
        long diff = end - start;

        System.out.println("\nTime to compute Fragment 1 was "+ diff+" milliseconds");
//
    }


    public static void fragNum1(){
        int n = 10000;
        int sum = 0;
        for(int i = 0; i<n;i++){
            sum++;
        }
    }
    public static void fragNum2(){
        int n = 10000;
        int sum = 0;
        for(int i = 0; i<n;i+=2){
            sum++;
        }
    }
    public static void fragNum3(){
        int n = 10000;
        int sum = 0;
        for(int i = 0; i<n;i++){
            for(int j = 0; j<n; j++) {
                sum++;
            }
        }
    }
    public static void fragNum4(){
        int n = 10000;
        int sum = 0;
        for(int i = 0; i<n;i++){
            sum++;
        }
        for(int j = 0;j<n;j++){
            sum++;
        }
    }
    public static void fragNum5(){
        int n = 10000;
        int sum = 0;
        for(int i = 0; i<n;i++){
            for(int j = 0; j<n*n; j++) {
                sum++;
            }
        }
    }
    public static void fragNum6(){
        int n = 10000;
        int sum = 0;
        for(int i = 0; i<n;i++){
            for(int j = 0; j<i; j++) {
                sum++;
            }
        }
    }
    public static void fragNum7(){
        int n = 10000;
        int sum = 0;
        for (int i = 0;i<n;i++){
            for (int j = 0; j<n*n;j++){
                for (int k =0;k<j;k++){
                    sum++;
                }
            }
        }
    }

    public static void fragNum8(){
        int n = 10000;
        int sum = 0;
        for(int i = 1; i<n;i = i *2){
            sum++;
        }
    }






}
