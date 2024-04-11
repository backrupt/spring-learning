import * as FetchUser from './FetchUser.js'
import * as ResponseStatusCode from './ResponseStatusCode.js'
import * as ResponseMessage from './ResponseMessage.js'


export const UserWriteFormComponent = () => {
    
    const userWriteAction = async() =>{
            if (document.f.userId.value == "") {
                alert("사용자 아이디를 입력하십시요.");
                f.userId.focus();
                return false;
            }
            if (document.f.password.value == "") {
                alert("비밀번호를 입력하십시요.");
                f.password.focus();
                return false;
            }
            if (document.f.password2.value == "") {
                alert("비밀번호확인을 입력하십시요.");
                f.password2.focus();
                return false;
            }
            if (document.f.name.value == "") {
                alert("이름을 입력하십시요.");
                f.name.focus();
                return false;
            }
            if (document.f.email.value == "") {
                alert("이메일 주소를 입력하십시요.");
                f.email.focus();
                return false;
            }
            if (document.f.password.value != document.f.password2.value) {
                alert("비밀번호와 비밀번호확인은 일치하여야합니다.");
                f.password.focus();
                f.password.select();
                return false;
            }
        let userJsonObject = {
            userId:document.f.userId.value,
            password:document.f.password.value,
            name:document.f.name.value,
            email:document.f.email.value,
        }
        const responseJsonObject = await FetchUser.userWriteAction(userJsonObject);
        
        console.log(responseJsonObject);
        if(responseJsonObject.status == ResponseStatusCode.CREATED_USER){
            //성공할경우 로그인 폼으로 이동
            location.hash='#/user_login_form';
        } else if(responseJsonObject.status == ResponseStatusCode.CREATE_FAIL_EXISTED_USER){
            //실패할경우 메세지출력
            document.querySelector('#idMessage').innerHTML = ResponseMessage.CREATE_FAIL_EXISTED_USER;
            
        }
    };

    const template = `
        <table width="0" border="0" cellpadding="0" cellspacing="0">
            <tbody>
            <tr>
                <td>
                    <!--contents--> <br>
                    <table style="padding-left: 10px" border="0" cellpadding="0"
                        cellspacing="0">
                        <tbody>
                            <tr>
                                <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 -
                                        회원 가입</b></td>
                            </tr>
                        </tbody>
                    </table> <!-- write Form  -->
                    <form name="f" method="post">
                        <table border="0" cellpadding="0" cellspacing="1" width="590"
                            bgcolor="BBBBBB">
                            <tbody>
                                <tr>
                                    <td width="100" align="center" bgcolor="E6ECDE" height="22">사용자
                                        아이디</td>
                                     <td width="490" bgcolor="ffffff" style="padding-left: 10px"
                                        align="left"><input type="text" style="width: 150px"
                                        name="userId" value="">&nbsp;&nbsp;<font color="red" id='idMessage'></font>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100" align="center" bgcolor="E6ECDE" height="22">비밀번호</td>
                                    <td width="490" bgcolor="ffffff" style="padding-left: 10px"
                                        align="left"><input type="password" style="width: 150px"
                                        name="password" value=""></td>
                                </tr>
                                <tr>
                                    <td width="100" align="center" bgcolor="E6ECDE" height="22">비밀번호
                                        확인</td>
                                    <td width="490" bgcolor="ffffff" style="padding-left: 10px"
                                        align="left"><input type="password" style="width: 150px"
                                        name="password2" value=""></td>
                                </tr>
                                <tr>
                                    <td width="100" align="center" bgcolor="E6ECDE" height="22">이름</td>
                                    <td width="490" bgcolor="ffffff" style="padding-left: 10px"
                                        align="left"><input type="text" style="width: 150px"
                                        name="name" value=""></td>
                                </tr>
                                <tr>
                                    <td width="100" align="center" bgcolor="E6ECDE" height="22">이메일
                                        주소</td>
                                    <td width="490" bgcolor="ffffff" style="padding-left: 10px"
                                        align="left"><input type="text" style="width: 150px"
                                        name="email" value=""></td>
                                </tr>
                            </tbody>
                        </table>
                    </form> <br>

                    <table border="0" cellpadding="0" cellspacing="1">
                        <tbody>
                            <tr>
                                <td align="center">
                                <input type="button" id="btn_user_write_action" value="회원 가입"> &nbsp;</td>
                            </tr>
                        </tbody>
                    </table>

                </td>
            </tr>
        </tbody>
    </table>
`;
    document.querySelector("#content").innerHTML = template;
    //이벤트 처리
    document.querySelector('#btn_user_write_action').onclick = userWriteAction;
}