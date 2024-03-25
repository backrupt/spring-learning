package com.itwill.user.mapper;

import java.util.List;

import com.itwill.user.User;

public interface UserMapper {
	
	int insert(User user) ;
	/*
	<insert id="insert" parameterType="com.itwill.user.User">
			insert into userinfo(userid,password,name,email) 
			values(#{userId},#{password},#{name},#{email})
	</insert>
	*/
	
	
	public int update(User user);
	/*
	<update id="update" parameterType="com.itwill.user.User">
		update  userinfo set password=#{password},name=#{name},email=#{email} where userid=#{userId}
	</update>
	*/
	
	public int delete(String userId) throws Exception;
	/*
	<delete id="delete" parameterType="java.lang.String">
		delete userinfo where userid=#{userId}
	</delete>
	 */
	
	public User findUser(String userId);
	/*
	<select id="findUser" parameterType="string" resultType="com.itwill.user.User">
		select userid,password,name,email from userinfo where userid=#{userId}
	</select>
   */
	public List<User> findUserList();
	/*
	<select id="findUserList" resultType="com.itwill.user.User">
		select userid,password,name,email from userinfo
	</select>
   */
	int countByUserId(String userId) throws Exception;
	/*
	<select id="countByUserId" 	parameterType="java.lang.String" 
								resultType="java.lang.Integer">
		select count(*) cnt from userinfo where userid=#{userId}
	</select>
	*/
	
	
	

	
}