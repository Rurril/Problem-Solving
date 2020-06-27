# Atcoder Beginner Contest 172 - C : [Tsundoku](https://atcoder.jp/contests/abc172/tasks)

## 접근

두 책장이라는 스택에서, 책을 뽑아서 제한 시간 내에 가장 많이 읽을 수 있는 책의 양을 구하는 문제.

---
## 풀이

### 누적합(Prefix sum) 구하기 

```java
temp = br.readLine().split(" ");
for(int i=1;i<=a;i++){
    sumA[i] = sumA[i-1] + Long.parseLong(temp[i-1]);

}

temp = br.readLine().split(" ");
for(int i=1; i<=b;i++){
    sumB[i] = sumB[i-1] + Long.parseLong(temp[i-1]);
}
```

책의 i권 까지 읽으면 걸리는 시간을 sum[i]에 저장했다.


### 투 포인터(Two Pointers) 방식 적용

```java
int j = b;
for(int i=0;i<=a;i++){


    if(sumA[i] > time)break;

    while(sumB[j] > time - sumA[i])j--;

    max = Math.max(max, i+j);

}
```

sumA을 가리키는 i를 앞에서 sumB를 가리키는 j를 뒤에서 한칸씩 당기면서

그 중 최고 값을 구한다. 


--- 
## 🔥 시행착오

1. 투 포인터가 떠오르지 않았고, DFS, BF 등 다양한 방식을 시도하다가 결국 풀지 못했다.
2. 투 포인터 가까이 접근했지만, 완전히 적용하지 못했고 결국 시간초과로 터져버렸다.


## 🤭 비슷한 문제

백준 온라인 저지
- 


## 💌 참고 자료

[ABC172_C.java](https://github.com/Rurril/Problem-Solving/blob/Test/Problem-Solving/PS/TwoPointers/ABC172_C.java)


