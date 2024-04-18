import * as FetchUser from "./FetchUser.js";
import * as ResponseStatusCode from "./ResponseStatusCode.js";
import * as ResponseMessage from "./ResponseMessage.js";
export const UserLeftMenuComponent =async() => {
  /******로그아웃함수 ***********/
  const userLogoutAction = async ()=>{
	  console.log('>>>>>>>>>>>');
	  const responseJsobObject = 
	  await FetchUser.userLogoutAction();
	  //page reloading
	  location.reload();
	  
  }
  const responseJsonObject = await  FetchUser.userLoginCheck(); 
  const template = `
			  <p>
			    <strong>메뉴</strong>
			  </p>
			  <ul>
			  		${
						(responseJsonObject.status==ResponseStatusCode.LOGIN_SUCCESS)?
						`
							<!-- 로그인후 -->
							<li><a href="#">${responseJsonObject.data.userId} 님</a></li>
							<li><a href="#/user_view/${responseJsonObject.data.userId}">내정보</a></li>
							<li><a href="#" id="a_user_logout">로그아웃</a></li>`
						:   `<!-- 로그인전 -->
							<li><a href="#/user_login_form">로그인</a></li>
							<li><a href="#/user_write_form">회원가입</a></li>` 
					}
				    <li><a href='http://localhost:8080/swagger-ui/index.html'>Swagger</a></li>
			  </ul>`;
  			  document.querySelector("#navigation").innerHTML = template;
  			  if(!!document.querySelector("#a_user_logout")){
  			  	document.querySelector("#a_user_logout").onclick = userLogoutAction;
  			  }
  			  
};











