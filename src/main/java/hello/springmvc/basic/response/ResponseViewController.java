package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *  정적 리소스
 *    - 스프링 부트는 클래스패스의 다음 디렉토리에 있는 정적 리소스를 제공한다. /static , /public , /resources , /META-INF/resources
 *    - src/main/resources 는 리소스를 보관하는 곳이고, 또 클래스패스의 시작 경로이다. 따라서 다음 디렉토리에 리소스를 넣어두면 스프링 부트가 정적 리소스로 서비스를 제공한다.
 *
 *  정적 리소스 경로
 *   - src/main/resources/static
 *   - 다음 경로에 파일이 들어있으면
 *     src/main/resources/static/basic/hello-form.html
 *   - 웹 브라우저에서 다음과 같이 실행하면 된다. http://localhost:8080/basic/hello-form.html
 *  정적 리소스는 해당 파일을 변경 없이 그대로 서비스하는 것이다.
 *
 *  String을 반환하는 경우 - View or HTTP 메시지
 *    - @ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다.
 *    - @ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는 문자가 입력된다
 *
 *  여기서는 뷰의 논리 이름인 response/hello 를 반환하면 다음 경로의 뷰 템플릿이 렌더링 되는 것을 확인할 수 있다.
 *  실행: templates/response/hello.html
 *
 *  Void를 반환하는 경우
 *   - @Controller 를 사용하고, HttpServletResponse , OutputStream(Writer) 같은 HTTP 메시지
 *   - 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용 요청 URL: /response/hello
 *   - 실행: templates/response/hello.html
 *   - 참고로 이 방식은 명시성이 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없어서, 권장하지 않는다.
 *
 *  HTTP 메시지
 *   - @ResponseBody , HttpEntity 를 사용하면, 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 응답 데이터를 출력할 수 있다.
 *
 */
@Controller
public class ResponseViewController {

    @RequestMapping("/reponse-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");
        return "response/hello";
    }

    /**
     * 컨트롤러의 경로 이름과 뷰의 논리적인 이름이 똑같으면 반환 값을 적지 않아도 스프링부트에서
     * 알아서 논리적 이름으로 반환해준다. 하지만 추천하지 않음.
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }
}
