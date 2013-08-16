package heaps;

public class HeapUtils 
{
	/*
	 * This algorithm is taken from CLR. The only modification is the change to 0 index arrays from 1
	 * index given in CLR.
	 *
	 */
	public static int parent(int i) 
	{
		return ((i+1)/2) - 1;
	}

	public static int left(int i) 
	{
		return 2*i + 1;
	}

	public static int right(int i) 
	{
		return 2*i + 2;
	}

	/* Complexity is of heapify is log n, as it takes log n comparisons to get the element in the right place*/
	public static void heapify(int a[], int i, int heapSize) 
	{
		int l = left(i);
		int r = right(i);
		int largest = i;
		if (l < heapSize && a[l] > a[i]) 
		{
			largest = l;
		}
		if (r < heapSize && a[r] > a[largest]) 
		{
			largest = r;
		}
		if (i != largest) 
		{
			int tmp = a[i];
			a[i] = a[largest];
			a[largest] = tmp;
			heapify(a, largest, heapSize);
		}
	}

	public static void buildHeap(int a[], int heapSize) 
	{
		//int lastParent = heapSize / 2; /*also works*/
		int lastParent = heapSize / 2 - 1;
		for (int i = lastParent; i >= 0; i--) 
		{
			heapify(a, i, heapSize);
		}
	}	

	public static void heapsort(int a[]) 
	{
		buildHeap(a, a.length);
		int heapSize = a.length;
		for (int i = a.length - 1; i > 0; i--) 
		{
			int tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			heapSize--;
			heapify(a, 0, heapSize);
		}
	}

	public static String printArray(int a[]) 
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < a.length; i++) 
		{
			sb.append(a[i]).append(",");
		}
		return sb.toString();
	}

	public static void main(String args[]) 
	{
		String numbers[] = args[0].split(",");
		int a[] = new int[numbers.length];
		for (int i = 0; i < a.length; i++) 
		{
			a[i] = Integer.parseInt(numbers[i]);
		}
		heapsort(a);
		System.out.println("Heap Sort: " + printArray(a));
		PriorityQueue pq = new PriorityQueue();
		for (int i = 0; i < a.length; i++) 
		{
			pq.insert(a[i]);
		}
		while (!pq.isEmpty()) 
		{
			System.out.println(pq.extractMax());
		}
	}
}

/*
 * Implementation of a PriorityQueue using a Heap
 *
 */

class PriorityQueue 
{
	final int MAX_QUEUE_SIZE = 100;
	final int ERROR_CODE = -1;

	int a[];
	int heapSize;

	public PriorityQueue() 
	{
		a = new int[MAX_QUEUE_SIZE];
		heapSize = 0;
	}

	public int extractMax()
	{
		if (heapSize <= 0) 
		{
			return ERROR_CODE;
		}
		int max = a[0];
		a[0] = a[heapSize - 1];
		heapSize--;
		HeapUtils.heapify(a, 0, heapSize);
		return max;
	}

	public boolean insert(int x) 
	{
		if (heapSize >= MAX_QUEUE_SIZE) 
		{
			return false;
		}
		heapSize++;
		int i = heapSize - 1;
		//change the following condition to HeapUtils.parent(i) > 0 
		while (i > 0 && a[HeapUtils.parent(i)] < x) 
		{
			a[i] = a[HeapUtils.parent(i)];
			i = HeapUtils.parent(i);
		}
		a[i] = x;
		return true;
	}

	public boolean isEmpty() 
	{
		return (heapSize == 0);
	}
}
