package com.socialweb.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserManager {
    	public User getUser(String username) throws UsernameNotFoundException;
            /*
             * ����� ��������� ������������. 
             * ������ ���������� ������ ���� User, ���������� �� ����.
             * � ������, ���� ������������ � ����� ������� �� ������,
             * �������� UsernameNotFoundException
            */
	
}
