package com.itwill.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itwill.user.exception.ExistedUserException;
import com.itwill.user.exception.PasswordMismatchException;
import com.itwill.user.exception.UserNotFoundException;

/*
 * - 회원관리 업무(비즈니스로직,예외처리,트랜젝션,보안,로깅)을 수행하는 클래스
 * - 웹컴포넌트(서블릿,JSP)에서 직접접근(메쏘드호출)하는 클래스(객체)
 * - Dao를 이용해서 데이타베이스를 조작작업(CRUD)하는 클래스
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDaoImplMyBatis")
	private UserDao userDao;
	public UserServiceImpl() throws Exception{
		
	}
	/*
	 * 회원가입
	 */
	@Override
	public int create(User user)throws Exception{
		/*
		 * -1:아이디중복
		 *  1:회원가입성공
		 */
		/*
		if(userDao.countByUserId(user.getUserId())==1) {
			//아이디중복
			return -1;
		}else {
			//아이디안중복
			//회원가입
			userDao.insert(user);
			return 1;
		}
		*/
		if (userDao.countByUserId(user.getUserId()) == 1) {
			//아이디중복
			throw new ExistedUserException(user.getUserId()+ "는 이미존재하는 아이디입니다.");
		}
		// 회원가입
		return userDao.insert(user);
	}
	/*
	 * 회원로그인
	 *  아이디존재안함 --> throw UserNotFoundException
	 * 	패쓰워드 불일치--> throw PasswordMismatchException
	 *  로그인성공(세션)
	 */
	@Override
	public int login(String userId,String password) throws Exception{
		/*
		int result=-1;
		//1.아이디존재여부
		User user = userDao.findUser(userId);
		if(user==null) {
			//아이디존재안함
			result=0;
		}else {
			//아이디존재함
			if(user.getPassword().equals(password)) {
				//패쓰워드일치
				result=2;
			}else {
				//패쓰워드불일치
				result=1;
			}
		}
		return result;
		*/
		// 1.아이디존재여부
				User user = userDao.findUser(userId);
				if (user == null) {
					// 아이디존재안함
					throw new UserNotFoundException(userId + " 는 존재하지 않는 아이디 입니다.");
				} 
				if (!user.getPassword().equals(password)) {
					//패쓰워드불일치
					throw new PasswordMismatchException("패쓰워드가 일치하지않습니다.");
				}
				//로그인성공
				return 0;
	}
	/*
	 * 회원상세보기
	 */
	@Override
	public User findUser(String userId)throws Exception{
		return userDao.findUser(userId);
	}
	/*
	 * 회원수정
	 */
	@Override
	public int update(User user)throws Exception{
		return userDao.update(user);
	}
	
	/*
	 * 회원탈퇴
	 */
	@Override
	public int remove(String userId)throws Exception{
		return userDao.delete(userId);
	}
	
}


















