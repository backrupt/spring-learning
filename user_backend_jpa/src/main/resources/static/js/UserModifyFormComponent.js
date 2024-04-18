import  * as FetchUser  from "./FetchUser.js";
import  * as ResponseStatusCode  from "./ResponseStatusCode.js";

export const UserModifyFormComponent = async (userId) => {
  /***********사용자정보수정함수********************/
  const userModifyAction= async ()=>{
		if (document.f.userId.value == "") {
			alert("사용자 아이디를 입력하십시요.");
			document.f.userId.focus();
			return;
		}
		if (document.f.password.value == "") {
			alert("비밀번호를 입력하십시요.");
			document.f.password.focus();
			return;
		}
		if (document.f.password2.value == "") {
			alert("비밀번호확인을 입력하십시요.");
			f.password2.focus();
			return;
		}
		if (document.f.name.value == "") {
			alert("이름을 입력하십시요.");
			f.name.focus();
			return;
		}
		if (document.f.email.value == "") {
			alert("이메일 주소를 입력하십시요.");
			f.email.focus();
			return;
		}
		if (document.f.password.value != f.password2.value) {
			alert("비밀번호와 비밀번호확인은 일치하여야합니다.");
			f.password.focus();
			f.password.select();
			return;
		}
		let sendJsonObject={
			userId:document.f.userId.value,
			password:document.f.password.value,
			name:document.f.name.value,
			email:document.f.email.value,
		};
		const responseJsonObject = await  FetchUser.userModifyAction(sendJsonObject);
		if(responseJsonObject.status==ResponseStatusCode.UPDATE_USER){
			//사용자 정보보기 화면으로 이동
			location.hash=`#/user_view/${responseJsonObject.data.userId}`;
		}
  }
  /***********************************************/

  const responseJsonObject = await FetchUser.userView(userId);	
  const user = responseJsonObject.data;
  const template = 
  `<table border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td>
				<!--contents--> <br>
				<table style="padding-left: 10px" border="0" cellpadding="0"
					cellspacing="0">
					<tbody>
						<tr>
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 -
									사용자수정</b></td>
						</tr>
					</tbody>
				</table> <!-- update Form  -->
				<form name="f" method="post">
					<input type="hidden" name="userId" value="${user.userId}">
					<table border="0" cellpadding="0" cellspacing="1" width="590"
						bgcolor="BBBBBB">
						<tbody>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">사용자
									아이디</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10px"
									align="left">${user.userId}</td>
							</tr>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">비밀번호</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10px"
									align="left"><input type="password" style="width: 150px"
									name="password" value="${user.password}"></td>
							</tr>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">비밀번호
									확인</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10px"
									align="left"><input type="password" style="width: 150px"
									name="password2" value="${user.password}"></td>
							</tr>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">이름</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10px"
									align="left"><input type="text" style="width: 150px"
									name="name" value="${user.name}"></td>
							</tr>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">이메일
									주소</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10px"
									align="left"><input type="text" style="width: 150px"
									name="email" value="${user.email}"></td>
							</tr>
						</tbody>
					</table>
				</form> <br>

				<table width="590" border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td align="center">
							<input id="btn_user_modify_action" type="button" value="수정" > &nbsp;</td>
						</tr>
					</tbody>
				</table>

			</td>
		</tr>
	</tbody>
	</table>`;
	
 	document.querySelector("#content").innerHTML = template;
 	document.querySelector("#btn_user_modify_action").onclick = userModifyAction;
};
