package com.itwill.guest.test;

import com.itwill.guest.GuestDao;
import com.itwill.guest.GuestDaoImplMyBatis;

public class GuestDaoTestMain {

	public static void main(String[] args) {
		GuestDao guestDao=new GuestDaoImplMyBatis();
		System.out.println("### guestDao:"+guestDao);
	}

}
