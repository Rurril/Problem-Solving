# Atcoder ABC171 - E

## 접근

XOR 연산이 가지고 있는 특성을 알고 있어야지 풀 수 있는 수학 문제

A ^ B ^ B = A 와 같다는 점을 이용할 수 있어야 한다.


---
## 풀이

### 전체에서 해당하는 부분만 다시 XOR 연산

```java
for(int i=1;i<=N;i++){
    t ^= arr[i];
}

for(int i=1;i<=N;i++){
    System.out.print((t ^ arr[i]) + " ");
}
```

전체를 XOR 연산한 것에서 그 부분만 빼서 원하는 값들을 추출했다.

--- 
## 🔥 시행착오

1. 분할 정복을 통해서 풀려고 했는데, 시간초과가 나길래 왜지? 했는데, O(N)으로 풀 수 있는 더 빠른 방법이 있는 것을 알 수 있었다.

## 🤭 비슷한 문제

백준 온라인 저지
- 


## 💌 참고 자료

> [RedScarf.java](https://github.com/Rurril/Problem-Solving/blob/Test/Problem-Solving/PS/Math/RedScarf.java) 