package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // synchronized 이용 -> transactional 어노테이션 땜에 커밋 전에 다른 쓰레드가 데이터 가져감 == 실패
//    @Transactional
//    public synchronized void decrease(Long id, Long quantity) {
//        Stock stock = stockRepository.findById(id).orElseThrow();
//        stock.decrease(quantity);
//        stockRepository.saveAndFlush(stock);
//    }

    // synchronized 이용 -> 성공
//    public synchronized void decrease(Long id, Long quantity) {
//        Stock stock = stockRepository.findById(id).orElseThrow();
//        stock.decrease(quantity);
//        stockRepository.saveAndFlush(stock);
//    }

    /**
     * synchronized 문제
     * 1개 프로세스에서만 작동 -> 멀티 서버 운용 불가능
     */

    /**
     * DB LOCK
     * 1. Pessimistic Lock
     * 실제로 데이터에 Lock 을 걸어서 정합성을 맞추는 방법
     * exclusive lock 을 걸게되며 다른 트랜잭션에서는 lock 이 해제되기전에 데이터를 가져갈 수 없게됨
     * 데드락이 걸릴 수 있음
     * low, table 단위
     * 충돌 빈번하면 좋음, 별도 락 때문에 성능 감소함
     *
     * 2. Optimistic Lock
     * 실제로 Lock 을 이용하지 않고 버전을 이용함으로써 정합성을 맞추는 방법
     * 먼저 데이터를 읽은 후에 update 를 수행할 때 현재 내가 읽은 버전이 맞는지 확인하며 업데이트 함
     * 내가 읽은 버전에서 수정사항이 생겼을 경우에는 application에서 다시 읽은후에 작업을 수행
     * 별도 락 x, 실패시 처리 로직 개발해야함
     * 충돌 빈번하지않으면 이거 추천
     *
     * 3. Named Lock
     * 이름을 가진 metadata locking
     * 이름을 가진 lock 을 획득한 후 해제할때까지 다른 세션은 이 lock 을 획득할 수 없도록 함
     * transaction이 종료될 때 lock이 자동으로 해제되지 않음
     * 별도의 명령어로 해제를 수행해주거나 선점시간이 끝나야 해제됨
     * lock 레포는 db 레포와 다른거 사용 추천
     * 주로 분산락에 사용
     */

    /**
     * redis lock
     * 1. lectuce
     * spin lock 방식
     * redis 부하감
     * 재시도 필요없으면 사용
     *
     * 2. redisson
     * pub-sub 방식
     * redis 부하 줄여줌
     * 재시도 필요하면 사용
     *
     */

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
