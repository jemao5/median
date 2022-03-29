public class Median {
    public static void main(String[] args) {
        int[] a = { 3, 9, 1, 14, 2, 10, 13, 5, 8, 6, 15, 16, 4, 7, 12, 11 };
        System.out.println(median(a));
    }

    // quicksort method now renamed to median
    public static double median(int[] a) {
        return quick(a, 0, a.length-1);
    }

    /*
     * The general idea comes in two pieces.
     *
     * 1. Make sure to perform quicksort-like pivoting only on the side of the remaining
     *    data that needs it.  We only need the median in the right place; the rest of the
     *    data is extraneous work, so minimize that work.
     * 2. If we have an odd number of data, it is easy to tell if we have the median because
     *    there is a single middle element of the array.  If we have an even number of data,
     *    we need to know if we are at position n/2 or (n/2)-1.  Depending on which side of
     *    the median we have landed, we use one iteration of selection sort to get the biggest
     *    datum on the left or the smallest datum on the right so we can take the mean of the
     *    two data that comprise the median.
     */
    public static double quick(int[] a, int left, int right) {
        if (right > left) {
            int pivotPos = partition(a, left, right);
            int middle = a.length/2;

            // Test for pivot position.  The first case is if the array length is
            // odd with a single middle; the else is for even array length with two middles.
            if (a.length % 2 == 1) {
                if (pivotPos == middle) return (a[middle]);
            } else {  // Even number of elements
                // Approach due to Naveen Ram, Nico Diaz-Wahl, and Jonne Kaunisto:
                // Once one of the two middle indices has been found, borrow from
                // selection sort and select either the minimum value from the right
                // side or the maximum value from the left side, depending on which side
                // does not contain the found middle index.
                //
                // pivotPos == middle looks like xxxxxPxxxx
                if (pivotPos == middle) {
                    return (a[middle] + select(a, left, middle-1, 1)) / 2.0;

                    // pivotPos == middle - 1 looks like xxxxPxxxxx
                } else if (pivotPos == middle - 1) {
                    return (a[middle-1] + select(a, middle, right, 0)) / 2.0;
                }
            }

            // Here is where we make sure to "sort" the elements that move us
            // towards the median element(s)
            if (pivotPos < middle) {
                return quick(a, pivotPos+1, right);
            } else {
                return quick(a, left, pivotPos-1);
            }
            // If we ever get to a situation where we are "sorting" one element,
            // it must be a middle element
        } else {
            return a[left];
        }
    }

    public static int partition(int[] a, int left, int right) {
        int splitPos = left;
        for (int i = left; i < right; i++) {
            if (a[i] < a[right]) {
                swap(a, i, splitPos);
                splitPos++;
            }
        }
        swap(a,splitPos,right);
        return splitPos;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // select: if minmax = 0, find the min; otherwise find the max
    // in the array between indices left and right
    public static int select(int[] a, int left, int right, int minmax) {
        int returnValue = a[left];
        if (minmax == 0) { // find minimum
            for (int i = left; i <= right; i++) {
                if (a[i] < returnValue) {
                    returnValue = a[i];
                }
            }

        } else {
            for (int i = left; i <= right; i++) {
                if (a[i] > returnValue) {
                    returnValue = a[i];
                }
            }
        }
        return returnValue;
    }
}
