public class Median {
    public static void main(String[] args){
        int[] tenInts = new int[]{1, 5, 2, 8, 3, 0, 7, 4, 9, 6};
        median(tenInts);
    }

    public static void swap(int[] a, int indx1, int indx2){
        int temp = a[indx1];
        a[indx1] = a[indx2];
        a[indx2] = temp;
    }

    public static int median(int[] a){
        int pivotIndex = 0;
        int[] temp = new int[a.length];
        System.arraycopy(a, 0, temp, 0, a.length - 1 + 1);
        if(a.length % 2 != 0) {
            while(pivotIndex != a.length/2)
        }
        pivotIndex = pass(temp, 0, a.length -1);
        return 1;
    }

    public static int pass(int[] a, int high, int low){
        int pivot = high;
        int lowIndex = 0;
        int highIndex = 0;
        for(int i = 0; i<=high; i++){
            if(a[i] > pivot){
                lowIndex = i;
                break;
            }
        }
        for(int i = low; i>=0; i--){
            if(a[i] > pivot){
                highIndex = i;
                break;
            }
        }
        if(highIndex>lowIndex) {
            swap(a, lowIndex, highIndex);
            pass(a, low, high);
        } else {
            swap(a, lowIndex, high);
            return(lowIndex);
        }
    }

}
