import * as userApi from '../api/userApi.js'
import * as responseStatusCode from '../api/responseStatusCode.js'
import * as responseMessage from '../api/responseMessage.js'

export const UserLoginFormPage = ()=>{
    const userLoginAction = async()=>{
        if (document.f.userId.value == "") {
			alert("사용자 아이디를 입력하십시요.");
			document.f.userId.focus();
			return false;
		}
		if (document.f.password.value == "") {
			alert("비밀번호를 입력하십시요.");
			document.f.password.focus();
			return false;
		}

        const sendJsonObject = {
            userId:document.f.userId.value,
            password:document.f.password.value,
        }
        const responseJsonObject = await userApi.userLoginAction(sendJsonObject);
        console.log(responseJsonObject);
        switch(responseJsonObject.status){
            case responseStatusCode.LOGIN_SUCCESS:
                //location.href='user.html';
                //location.reload();
                location.replace("index.html");
                //page loading
                break;
            case responseStatusCode.LOGIN_FAIL_NOT_FOUND_USER:
                document.querySelector('#passwordMessage').innerHTML = '';
                document.querySelector('#idMessage').innerHTML = 
                responseMessage.LOGIN_FAIL_NOT_FOUND_USER;
                break;
                case responseStatusCode.LOGIN_FAIL_PASSWORD_MISMATCH_USER:
                document.querySelector('#idMessage').innerHTML = '';
                document.querySelector('#passwordMessage').innerHTML = 
                responseMessage.LOGIN_FAIL_PASSWORD_MISMATCH_USER;
                break;
        }
    }

    const template = `
    <table border=0 cellpadding=0 cellspacing=0>
        <tr>
            <td><br />
                <table style="padding-left: 10px" border=0 cellpadding=0
                    cellspacing=0>
                    <tr>
                        <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 -
                                로그인</b></td>
                    </tr>
                </table> <!-- login Form  -->
                <form name="f" method="post">
                    <table border="0" cellpadding="0" cellspacing="1" bgcolor="BBBBBB">
                        <tr>
                            <td width=100 align=center bgcolor="E6ECDE" height="22">사용자
                                아이디</td>
                            <td width=490 align="left" bgcolor="ffffff"
                                style="padding-left: 10px"><input type="text"
                                style="width: 150" name="userId" value="">&nbsp;&nbsp;<font
                                color="red" id='idMessage'></font></td>
                        </tr>
                        <tr>
                            <td width=100 align=center bgcolor="E6ECDE" height="22">비밀번호</td>
                            <td width=490 align="left" bgcolor="ffffff"
                                style="padding-left: 10px"><input type="password"
                                style="width: 150" name="password" value="">&nbsp;&nbsp;<font
                                color="red" id='passwordMessage'></font></td>
                        </tr>
                    </table>
                </form> <br />
                <table border="0" cellpadding="0" cellspacing="1">
                    <tr>
                        <td align=center><input type="button" value="로그인" id="btn_user_login_action"> &nbsp; 
                        <input type="button" value="회원가입폼" id="btn_user_write_form" data-navigate='#/user_write_form'></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    `;
    const pageObject = {
        template,
        render(){
            document.querySelector('#content').innerHTML = template;
            //이벤트
            document.querySelector('#btn_user_login_action').onclick = userLoginAction;
        }
    }
    return pageObject;
}