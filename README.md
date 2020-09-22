# 프로젝트

스프링MVC패턴을 활용한 기본CRUD 게시판


![스프링 기본](https://user-images.githubusercontent.com/44168355/93878240-b40ac780-fd14-11ea-83a7-1646b1706af7.png)





# 사용기술

* 사용기술
  * HTML / CSS / JavaScript / JQuery / Java / BootStrap / mybatis
* 개발환경
  * JDK8 / Oracle / spring / tomcat8.5 

# 주요기능

* 로그인 / 회원가입 / 로그아웃
* 글쓰기 / 리스트 / 수정 / 삭제
* 검색 / 페이징 / 댓글 / 첨부파일 업(다운)로드


## 회원가입

회원가입을 할때 아이디 중복 체크 및 개인정보는</br>
자바스크립트 정규식 및 Ajax를 활용해 동적으로 체크하였습니다

![테스트22](https://user-images.githubusercontent.com/44168355/93780490-333fc300-fc63-11ea-8d81-3e5ab6e0598b.png)

```JAVASCRIPT
var pwJ = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/; //비밀번호 정규식

//비밀번호 
$('#password').keyup(function() {
		
		if (pwJ.test($('#password').val())) {
			$('#passwordCheck').text("");
		} else {
			$('#passwordCheck').text('숫자와 영문조합으로 8자리 이상 입력해주세요');
			$('#passwordCheck').css('color', 'red');
		}
	});
```





## 로그인

로그인은 3가지 로 나뉘어 
이메일 인증을한회원 / 아이디가 비밀번호가 없는 회원 / 이메일도 있고 아이디도 있는 회원으로<br/>
구분하였습니다
```JAVA
$('#loginBtn').click(function(){
	var id = $('#inputId').val();
	var password = $('#inputPassword').val();
	var rememberUs = $('#rememberUs').is(':checked');
		$.ajax({
		type : "post",
		url : 'login.do',
		data : {
			id : id,
			password : password,
			rememberUserId : rememberUs
		},
				success : function(data){
				
				if(data == 0){
					$('#spanLoginCheck').text('로그인 정보를 정확히 입력해주세요')
				}else if (data == -2){
					$('#spanLoginCheck').text('이메일을 인증 해주셔야합니다')
					$('#spanLoginCheck').css('color', 'red');
				}else{
					location.href="/app/main/getMainList.do"
				}
				
			}
		})
})    
```

로그인이 있는 회원 같은경우 아이디 저장 기능을 체크한 회원과 체크하지 않은 회원을 구분해</br>
체크 한 경우 쿠키에 아이디정보를 추가하였습니다

```JAVA
//로그인 했을때
if(vo != null) {
    if(vo.getId().equals(userId) && vo.getPassword().equals(userPassword)) {


        Cookie cookie = new Cookie("userCheck", userId);

        if(userCheck.equals("true")) {
            response.addCookie(cookie);
            System.out.println("아이디 저장 O");
        }else {
            System.out.println("아이디 저장 X");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        System.out.println("3단계 : 로그인 단계");
        vo.setPassword("");



        httpSession.setAttribute("basketCount",UserDAO.basketCount(vo.getId()));

        httpSession.setAttribute("userSession", vo);
        System.out.println("회원아이디 세션 확인 userSession" + httpSession.getAttribute("userSession"));
        result = 1;

}
```        
## 상품등록

상품등록(proudctReg) 

카테고리/가격/할인%/배송비 브랜드 등 정보를 입력할 수 있으며 카테고리는 josnArray를 통하여 동적 생성 할 수 있도록 하였으며

썸머노트 에디터를 활용하여 상세페이지를 작성할수 있도록 하였습니다 대표이미지(썸네일) 이미지와 서브이미지를 추가할 수 있도록 구현하였습니다 <


![이미지22](https://user-images.githubusercontent.com/44168355/93786878-c2040e00-fc6a-11ea-8b5b-18b1b0d866ae.png)

## 장바구니

장바구니는 javascript를 활용하여 상품 삭제 및 결제 금액 할인 금액등을 계산하였습니다

버튼을 통하여 상품수량을 수정할 수 있으며 썸네일 이미지를 통하여 상품을 확인 할 수 있도록 하였습니다

![장바구니](https://user-images.githubusercontent.com/44168355/93787023-ee1f8f00-fc6a-11ea-85ae-3a1ed2fea5df.png)

### 결제

결제(order)는 javascipt를 통하여 상품 금액 및 개수를 확인하고 다음 주소찾기API를 통하여 배송지 정보를 입력 할 수 있도록 하였으며

결제 부분은 아임포트API를 통하여 구현하였으며 이니시스를 통하여 카카오 페이 결제를 진행 하도록 하였습니다

![결제33](https://user-images.githubusercontent.com/44168355/93789281-9e41c780-fc6c-11ea-85b1-bac736501897.png)

# 배운점

스프링 MVC패턴에 대하여 자세하게 경험하게 됬던 계기였습니다.

AJAX을이용하여 장바구니 카테고리 로그인 회원가입등 다양한 기능들을 동적으로 구현할 수 있었고

이미지업로드를 구현하여 절대경로와 상대경로를 자세히 알 수 있게 되었습니다.

다음주소 API및 아임포트 결제API를 통하여 API구현도 해 볼 수 있는 좋은 프로젝트 경험이었습니다.





