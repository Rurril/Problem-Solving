# 프로그래머스 - 무지의 먹방 라이브(2019 KAKAO BLIND RECRUITMENT)

## 접근

프로그래머스에서 난이도가 높은 축에 속하길래 지래 겁을 먹었지만, 사실 구현만 제대로 하면 되는 문제였다.

---
## 풀이

### 전체적으로 음식 먹기

```java
while(true){

    if(cnt == foods.length){
        return -1; // 다 먹어서 더 이상 먹을 음식이 없음.
    }

    totalEat += ((long)(foods[cnt] - eatSize))*(long)remain; // 만큼의 시간이 흐른 것
    if(totalEat > k){ // 남은 음식들을 모두 먹을 사이클이 되지 않은 경우. 끝
        totalEat -= ((long)(foods[cnt] - eatSize))*(long)remain; // 다시 원상 복구
//                System.out.println(totalEat);
        answer = eatRemains(food_times, eatSize, k -totalEat); // eatSize 이하의 접시들은 모두 먹어치운 것, 그리고 남은 먹을 음식들
        break;
    }
//            System.out.println(totalEat);
    eatSize = foods[cnt];
    cnt++;
    remain--;

}
```

foods 배열을 오름차순으로 정렬해서 가장 음식이 적게 들어있어있는 수를 기준으로 전부 먹어치우는 방식으로 진행했다.

만약 중간에 전체 먹은 음식양이 주어진 k보다 커지는 경우 - 그 사이클의 먹는 중간에 끝나는 것이므로

eatRemains 메서드를 통해서 먹방이 끝나고 몇 번째 그릇을 가리키는지 반환하도록 했다.

### 마지막 사이클 음식 먹기

```java
private static int eatRemains(int[] foods, int eatSize, long remainSize){

    ArrayList<Integer> remainDishs = new ArrayList<>();

    for(int i=0;i<foods.length;i++){
        if(foods[i] <= eatSize)continue;
        remainDishs.add(i+1);
    }

    long order = remainSize % remainDishs.size();
    return remainDishs.get((int)order);

//        while(true){
//
////            System.out.println(remainSize);
//            pointer++;
//            if(pointer == foods.length)pointer = 0;
//            if(foods[pointer] <= eatSize)continue;
//
//            if(remainSize == 0){
//                result = pointer+1;
//                break;
//            }
//
//            remainSize--;
//        }
//
//        return result;
}
```

처음에는 주석처리했던 while문으로 BF방식으로 일일히 구하려고 헀는데, 시간 초과나는 케이스들이 있어서 O(N)으로 구성해서 빠르게 풀었다. 

남은 음식을 먹는 과정이 복잡할 필요가 없던 것.


--- 
## 🔥 시행착오

1. eatRemains를 좀 더 나은 알고리즘으로 만드는 과정이 필요했다.
2. totalEats를 구하는 과정에서 int에서 버퍼 오버플로우가 나와서 long으로 캐스팅해주는 작업이 필요했다. 효율성 2번 문제에서 틀리는 바람에 찾다가 발견했다.





## 💌 참고 자료

[무지의_먹방_라이브.java](https://github.com/Rurril/Problem-Solving/blob/Test/Problem-Solving/PS/Simulation/PmuziMukbang.java)


