## Architecture Notes
- **MergeSort**: использует один общий буфер (reusable buffer), чтобы избежать повторных аллокаций.  
  Для маленьких подзадач (n ≤ 16) переключается на insertion sort.
- **QuickSort**: выбирает рандомизированный pivot и всегда рекурсирует в меньшую часть массива, а большую часть решает итеративно. Это гарантирует глубину стека O(log n).
- **Deterministic Select**: делит массив на группы по 5, рекурсирует только в одну сторону (и всегда в меньшую). Это также контролирует глубину.
- **Closest Pair**: рекурсивно делит по x-координате и поддерживает массив, отсортированный по y, чтобы сократить количество проверок до 7–8 соседей.

## Recurrence Analysis
- **MergeSort**: T(n) = 2T(n/2) + Θ(n). По Master theorem (Case 2) получаем Θ(n log n).
- **QuickSort**: T(n) = T(n/2) + Θ(n). В среднем случае это Master theorem (Case 2), даёт Θ(n log n).  
  Благодаря выбору меньшей части для рекурсии глубина ограничена O(log n).
- **Deterministic Select**: T(n) = T(n/5) + T(7n/10) + Θ(n). Это не подходит под классические Master cases,  
  но по Akra–Bazzi получаем Θ(n).
- **Closest Pair**: T(n) = 2T(n/2) + Θ(n). По Master theorem (Case 2) получаем Θ(n log n).

## Experimental Results

### Time vs n

### Depth vs n
## Observations
- MergeSort and QuickSort behave as expected: ~n log n growth.
- QuickSort sometimes faster, but variance exists due to random pivot choices.
- Deterministic Select has linear scaling, but with larger constant factors than sorting, so it is slower for n=10,000.
- Closest Pair also grows ~n log n, but its constants are high (due to extra geometric checks).
- Recursion depth matches theory: Select stays at ~1, QuickSort ≤ log n, MergeSort log n, Closest Pair slightly deeper but still logarithmic.

### Discussion of constant factors
- У QuickSort виден разброс времени из-за выбора pivot (хотя асимптотика совпадает).
- У Deterministic Select константы выше → в реальности он медленнее, чем Arrays.sort, несмотря на линейную асимптотику.
- GC (garbage collector) иногда добавляет скачки времени при больших n.
- Cache locality даёт преимущество MergeSort на больших массивах.

## Summary
- MergeSort и QuickSort показали поведение Θ(n log n), что совпало с теорией.
- Глубина рекурсии в QuickSort осталась ограниченной O(log n).
- Deterministic Select действительно линейный, но проигрывает по скорости сортировке на практике.
- Closest Pair показал ожидаемое Θ(n log n), при этом константные факторы были выше из-за strip-проверки.
- Общая картина: асимптотика совпадает, расхождения связаны с константами и особенностями JVM (JIT, GC, cache).
