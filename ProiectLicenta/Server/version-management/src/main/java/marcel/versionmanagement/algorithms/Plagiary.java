package marcel.versionmanagement.algorithms;

public class Plagiary {
    private byte[] file1;
    private byte[] file2;
    private int[][] distanceMatrix;

    public Plagiary(String file1, String file2) {
        this.file1 = file1.getBytes();
        this.file2 = file2.getBytes();
        this.distanceMatrix = new int[file1.length()][file2.length()];
        for(int i = 0; i < file1.length(); i++){
            this.distanceMatrix[i][0] = i;
        }
        for(int i = 0; i < file2.length(); i++){
            this.distanceMatrix[0][i] = i;
        }
    }

    public int calculateDistance(){
        int cost = 0;
        for(int j = 1; j < file2.length; j++){
            for(int i = 1; i < file1.length; i++){
                if(file1[i] == file2[j]){
                    cost = 0;
                } else {
                    cost = 1;
                }
                this.distanceMatrix[i][j] =  treeNumMin(this.distanceMatrix[i - 1][j] + 1,
                            this.distanceMatrix[i][j - 1] + 1,
                            this.distanceMatrix[i - 1][j - 1] + cost);
            }
        }
        return this.distanceMatrix[file1.length - 1][file2.length - 1];
    }

    private int treeNumMin(int number1, int number2, int number3){
        int min = number1;
        if(number2 < min){
            min = number2;
        }
        if(number3 < min){
            min = number3;
        }
        return min;
    }
}
