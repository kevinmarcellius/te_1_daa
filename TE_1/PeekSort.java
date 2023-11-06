package TE_1;

public class PeekSort {
    public static final boolean COUNT_MERGE_COSTS = true;
	/** total merge costs of all merge calls */
	public static long totalMergeCosts = 0;
	private static long memoryUsage;
    public static void peeksort(final int[] a, final int l, final int r) {
		
		int n = r - l + 1;
		peeksort(a, l, r, l, r, new int[n]);

	}

	public static long getTotalMergeCosts() {
		return totalMergeCosts;
	}


	// time complexity: O(n log n)
	// best case : O(n)
    public static void peeksort(int[] A, int left, int right, int leftRunEnd, int rightRunStart, final int[] B) {
		if (leftRunEnd == right || rightRunStart == left) return;

		int mid = left + ((right - left) >> 1);
		if (mid <= leftRunEnd) {
			// |XXXXXXXX|XX     X|
			peeksort(A, leftRunEnd+1, right, leftRunEnd+1,rightRunStart, B);
			mergeRuns(A, left, leftRunEnd+1, right, B);
		} else if (mid >= rightRunStart) {
			// |XX     X|XXXXXXXX|
			peeksort(A, left, rightRunStart-1, leftRunEnd, rightRunStart-1, B);
			mergeRuns(A, left, rightRunStart, right, B);
		} else {
			// find middle run
			final int i, j;
			if (A[mid] <= A[mid+1]) {
				i = extendWeaklyIncreasingRunLeft(A, mid, left == leftRunEnd ? left : leftRunEnd+1);
				j = mid+1 == rightRunStart ? mid : extendWeaklyIncreasingRunRight(A, mid+1, right == rightRunStart ? right : rightRunStart-1);
			} else {
				i = extendStrictlyDecreasingRunLeft(A, mid, left == leftRunEnd ? left : leftRunEnd+1);
				j = mid+1 == rightRunStart ? mid : extendStrictlyDecreasingRunRight(A, mid+1,right == rightRunStart ? right : rightRunStart-1);
				reverseRange(A, i, j);
			}
			if (i == left && j == right) return;
			if (mid - i < j - mid) {
				// |XX     x|xxxx   X|
				peeksort(A, left, i-1, leftRunEnd, i-1, B);
				peeksort(A, i, right, j, rightRunStart, B);
				mergeRuns(A,left, i, right, B);
			} else {
				// |XX   xxx|x      X|
				peeksort(A, left, j, leftRunEnd, i, B);
				peeksort(A, j+1, right, j+1, rightRunStart, B);
				mergeRuns(A,left, j+1, right, B);
			}
		}

        
	}

    // time complexity: O(n)
    // n = number of elements
    public static void mergeRuns(int[] A, int l, int m, int r, int[] B) {
		--m;// mismatch in convention with Sedgewick
		int i, j;
		assert B.length >= r+1;
		if (COUNT_MERGE_COSTS) totalMergeCosts += (r-l+1);
		for (i = m+1; i > l; --i) B[i-1] = A[i-1];
		for (j = m; j < r; ++j) B[r+m-j] = A[j+1];
		for (int k = l; k <= r; ++k)
			A[k] = B[j] < B[i] ? B[j--] : B[i++];
	}

    // time complexity: O(n)
    public static int extendWeaklyIncreasingRunLeft(final int[] A, int i, final int left) {
		while (i > left && A[i-1] <= A[i]) --i;
		return i;
	}

    // time complexity: O(n)
    public static int extendWeaklyIncreasingRunRight(final int[] A, int i, final int right) {
		while (i < right && A[i+1] >= A[i]) ++i;
		return i;
	}

    // time complexity: O(n)
    public static int extendStrictlyDecreasingRunLeft(final int[] A, int i, final int left) {
		while (i > left && A[i-1] > A[i]) --i;
		return i;
	}

    // time complexity: O(n)
	public static int extendStrictlyDecreasingRunRight(final int[] A, int i, final int right) {
		while (i < right && A[i+1] < A[i]) ++i;
		return i;
	}

    // time complexity: O(n)
    public static void reverseRange(int[] a, int lo, int hi) {
		while (lo < hi) {
			int t = a[lo]; a[lo++] = a[hi]; a[hi--] = t;
		}
	}
	
	public static long getMemoryUsage() {
		return memoryUsage;
	}

}
