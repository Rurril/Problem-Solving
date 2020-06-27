# Atcoder ABC 172 - D

## 접근

주어지는 수의 약수의 개수를 빠르게 구할 줄 알아야하는 수학 문제


---
## 풀이

### 약수의 개수를 구해서 결과값 도출

```java
for (int i = 1; i <= 10000000; i++) {
    for (int j = 1; j <= 10000000 / i; j++) {
        if(i*j > 10000000)break;
        divisors[i * j]++;
    }
}

for(int i=1;i<=N;i++){
    result += divisors[i] * i;
}
```


--- 
## 🔥 시행착오

1. 약수를 최대한 빠른 속도로 구할 수 있다면 되는 (방법을 알면) 쉬운 문제

## 🤭 비슷한 문제

백준 온라인 저지
- 


## 💌 참고 자료

> [ABC172_D.java](https://github.com/Rurril/Problem-Solving/blob/Test/Problem-Solving/PS/Math/ABC172_D.java) 