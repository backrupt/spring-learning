package com.itwill.guest;

public class GuestSQL {
	public static final String GUEST_INSERT_BEFORE_GUEST_NO = "select guest_guest_no_seq.nextval from dual";
	public static final String GUEST_INSERT = "insert into guest values(guest_guest_no_seq.currval,?,sysdate,?,?,?,?)";
	
	public static final String GUEST_UPDATE = "update guest set guest_name=?,guest_date=sysdate,guest_email=?,guest_homepage=?,guest_title=?,guest_content=? where guest_no=?";
	public static final String GUEST_DELETE = "delete from guest where guest_no=?";
	public static final String GUEST_SELECT_BY_NO = "select * from guest where guest_no=?";
	public static final String GUEST_SELECT_BY_NAME = "select * from guest where guest_name like '%'||?||'%'";
	public static final String GUEST_SELECT_BY_TITLE = 
			"select * from guest where guest_title like '%'||?||'%'";
	public static final String GUEST_SELECT_BY_CONTENT = "select * from guest where guest_content like '%'||?||'%'";
	public static final String GUEST_SELECT_ALL = "select * from guest";
}
