import  * as FetchUser  from "./FetchUser.js";

export const UserViewComponent = async (userId) => {
  
	
 /****************회원탈퇴함수*************/	
  const userDeleteAction=async(userId)=>{
	const responseJsonObject=FetchUser.userDeleteAction(userId);
	location.href='user.html';
  };
  /*************************************/		
  const responseJsonObject = await FetchUser.userView(userId);	
  const user = responseJsonObject.data;
  const template = `
  <table border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td>
				<!--contents--> <br>
				<table style="padding-left: 10px" border="0" cellpadding="0"
					cellspacing="0">
					<tbody>
						<tr>
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 -
									사용자 정보보기</b></td>
						</tr>
					</tbody>
				</table> <!-- view Form  -->
				<form name="f" method="post">
					<input type="hidden" name="userId" value="guard">
					<table border="0" cellpadding="0" cellspacing="1" width="590"
						bgcolor="BBBBBB">
						<tbody>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">사용자
									아이디</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10">
									${user.userId}</td>
							</tr>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">이름</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10">
									${user.name}</td>
							</tr>
							<tr>
								<td width="100" align="center" bgcolor="E6ECDE" height="22">이메일
									주소</td>
								<td width="490" bgcolor="ffffff" style="padding-left: 10">
									${user.email}</td>
							</tr>
						</tbody>
					</table>
				</form> <br>
				<table border="0" cellpadding="0" cellspacing="1">
					<tbody>
						<tr>
							<td align="center">
							<input id="btn_user_modify_form" data-navigate='#/user_modify_form/${userId}' type="button" value="수정폼" > &nbsp; 
							<input id="btn_user_delete_action" type="button" value="탈퇴"  > &nbsp;</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
 </table>`;
 document.querySelector("#content").innerHTML = template;
 document.querySelector("#btn_user_delete_action").onclick =function(){
	 userDeleteAction(userId);
 } 
};
