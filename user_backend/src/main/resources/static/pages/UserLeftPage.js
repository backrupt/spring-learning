import * as userApi from '../api/userApi.js';
import * as responseStatusCode from '../api/responseStatusCode.js';
async function UserLeftPage() {
  /*********** 로그아웃 함수 **********/
	const userLogoutAction = async()=>{
		console.log('발생해따');
		const responseJsonObject = await userApi.userLogoutAction(e);
    //page reloading
		location.replace('index.html');
    e.preventDefault();
	}

	const responseJsonObject = await userApi.userLoginCheck();
  console.log(responseJsonObject);
    const loginUser = 
    responseJsonObject.status==responseStatusCode.LOGIN_SUCCESS?responseJsonObject.data:{};
    const template = ` 
            <p>
              <strong>메뉴</strong>
            </p>
            <ul>
              <!-- 로그인후 -->
              ${(responseJsonObject.status==responseStatusCode.LOGIN_SUCCESS)?
                `
              <li><a href="#/user_view/${loginUser.userId}">${loginUser.userId}님</a></li>
              <li><a href="#/user_view/${loginUser.userId}">내정보</a></li>
              <li><a href="#" id="a_user_logout">로그아웃</a></li>
              `:`
              <!-- 로그인전 -->
              <li><a href="#/user_login_form">로그인</a></li>
              <li><a href="#/user_write_form">회원가입</a></li>
              `}
              <li><a href='http://localhost:8080/swagger-ui/index.html'>Swagger</a></li>
            </ul>`;
    const pageObject = {
      template: template,
      render: () => {
        document.querySelector("#navigation").innerHTML = template;
        //이벤트 취리취리뱅뱅
        if(responseJsonObject.status==responseStatusCode.LOGIN_SUCCESS){
          document.querySelector('#a_user_logout').addEventListener('click',userLogoutAction);
        }
      },
    };
    return pageObject;
  }
  export { UserLeftPage };