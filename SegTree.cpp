#include <bits/stdc++.h>
using ll = long long;

constexpr int size = 1e6 + 1;
int N, M, K;
ll arr[size], tree[size * 4];

ll init(int node, int start, int end)
{
    if (start == end)
        return tree[node] = arr[start];
    int mid = (start + end) / 2;
    return tree[node] = init(2 * node, start, mid) + init(2 * node + 1, mid + 1, end);
}

ll query(int node, int start, int end, int left, int right)
{
    if (left > end || start > right)
        return 0;
    if (left <= start && end <= right)
        return tree[node];
    int mid = (start + end) / 2;
    return query(2 * node, start, mid, left, right) + query(2 * node + 1, mid + 1, end, left, right);
}

void update(int node, int start, int end, int index, ll value)
{
    if (index < start || index > end)
        return;

    tree[node] += value;

    if (start != end)
    {
        int mid = (start + end) / 2;
        update(2 * node, start, mid, index, value);
        update(2 * node + 1, mid + 1, end, index, value);
    }
    return;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M >> K;

    for (int i = 1; i <= N; i++)
        std::cin >> arr[i];

    init(1, 1, N);

    for (int i = 0; i < M + K; i++)
    {
        int a, b;
        ll c;
        std::cin >> a >> b >> c;
        if (a == 1)
        {
            update(1, 1, N, b, c - arr[b]);
            arr[b] = c;
        }
        else
            std::cout << query(1, 1, N, b, c) << '\n';
    }
    return 0;
}