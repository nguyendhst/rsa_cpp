package test.nguyendhst.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BubbleSortTest {

    @Test
    public void testBubbleSort() {
        int[] arr = {1, 5, 3, 2, 4};
        BubbleSort.bubbleSort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}