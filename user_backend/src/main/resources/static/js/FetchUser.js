//const BACKEND_SERVER = "http://192.168.15.31:8080";
const BACKEND_SERVER = "";
const userWriteAction = async(sendJsonObject) => {
    const response = await fetch(`${BACKEND_SERVER}/user`,{
        method:'POST',
        headers:{
            'Content-type':'application/json'
        },
        body:JSON.stringify(sendJsonObject),
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

const userLoginCheck=async()=>{
    const response= await fetch(`${BACKEND_SERVER}/user/login`,{
        method:'GET'
    });
    const resultJsonObject=await response.json();
    return resultJsonObject;
}

const userLoginAction = async(sendJsonObject) =>{
    const response = await fetch(`${BACKEND_SERVER}/user/login`,{
        method:'POST',
        headers:{
            'Content-type':'application/json',
        },
        body:JSON.stringify(sendJsonObject),
    });
    const responseJsonObject = await response.json();
    return responseJsonObject;
}

const userLogoutAction = async()=>{
	const response = await fetch(`user/logout`,{
		method:'post',
	});
	const responseJsonObject = await response.json();
	return responseJsonObject;
}

const userView = async(userId)=>{
	const response = await fetch(`${BACKEND_SERVER}/user/${userId}`);
	const responseJsonObject = await response.json();
	console.log(responseJsonObject);
	return responseJsonObject;
}

const userModifyAction = async(sendJsonObject)=>{
	const response = await fetch(`user/${sendJsonObject.userId}`,{
		method:'put',
		headers:{
			'Content-type':'application/json',
		},
		body:JSON.stringify(sendJsonObject),
	});
	const responseJsonObject = await response.json();
	return responseJsonObject;
}

const userDeleteAction = async(userId)=>{
	const response = await fetch(`user/${userId}`,{
		method:'delete',
	});
	const responseJsonObject = await response.json();
	return responseJsonObject;
}

export {userWriteAction,userLoginAction,userLoginCheck,userView,userLogoutAction,userModifyAction,userDeleteAction}