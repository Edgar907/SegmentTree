import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for (int i = 0; i < N; i++) 
        {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];
        init(1, 0, N - 1);

        for (int i = 0; i < M + K; i++) 
        {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) 
            {
                long diff = c - arr[b - 1];
                arr[b - 1] = c;
                update(1, 0, N - 1, b - 1, diff);
            }
            else 
            {
                long q = query(1, 0, N - 1, b - 1, c - 1);
                sb.append(q).append('\n');
            }
        }
        System.out.print(sb);
    }

    static long init(int node, int start, int end) 
    {
        if (start == end) 
            return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    static void update(int node, int start, int end, long idx, long val) 
    {
        if (idx < start || idx > end)
            return;

        tree[node] += val;

        if (start != end)
        {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, val);
            update(node * 2 + 1, mid + 1, end, idx, val);
        }
    }

    static long query(int node, int start, int end, long left, long right) 
    {
        if (left > end || start > right)
            return 0;

        if (left <= start && end <= right) 
            return tree[node];

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
    }
}