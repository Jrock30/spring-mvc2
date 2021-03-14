package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController
 *   - @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
 *     @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
 *   - 따라서 실행 결과로 ok 메세지를 받을 수 있다. @ResponseBody 와 관련이 있는데, 뒤에서 더 자세히 설명한다.
 *
 * 로그 선언
 *   - private Logger log = LoggerFactory.getLogger(getClass());
 *   - private static final Logger log = LoggerFactory.getLogger(Xxx.class)
 *   - @Slf4j : 롬복 사용 가능
 *
 * 로그가 출력되는 포멧 확인
 *   - 시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스명, 로그 메시지
 *   - 로그 레벨 설정을 변경해서 출력 결과를 보자.
 *   - LEVEL: TRACE > DEBUG > INFO > WARN > ERROR 개발 서버는 debug 출력
 *   - 운영 서버는 info 출력
 *
 * 올바른 로그 사용법
 *   - log.debug("data="+data)
 *      - 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버린다.
 *   - 결과적으로 문자 더하기 연산이 발생한다. log.debug("data={}", data)
 *      - 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다. 따라서 앞과 같은 의미없는 연산이 발생하지 않는다.
 *
 */
@Slf4j
@RestController
public class LogTestController {

    // @Slf4j 를 하면 아래를 자동으로 해준다.
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "OK";

    }

}
