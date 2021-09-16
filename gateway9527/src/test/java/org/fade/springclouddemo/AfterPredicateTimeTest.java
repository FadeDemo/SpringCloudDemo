package org.fade.springclouddemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
@Slf4j
public class AfterPredicateTimeTest {

    @Test
    public void test() {
        ZonedDateTime now = ZonedDateTime.now();
        log.info(now.toString());
    }

}
