package com.eastpro.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class SimpleEncrypt {

    public static String encrypt(String userID, String password, boolean reformat_flag)
    {
        /**2016-05-25 
         * String  encrypt_str = com.security.TripleDes.encryptPassword(userID, password);
        if (reformat_flag)
        {
            encrypt_str = replaceStrForDB(encrypt_str);
        }**/
    	  Md5PasswordEncoder md5 = new Md5PasswordEncoder();
          md5.setEncodeHashAsBase64(false);
            System.out.println(">>>>>>>>>>>>>>>>>>>USERID = "+userID + "PWD ="+md5.encodePassword(userID,password));         
        return   md5.encodePassword(userID,password); 
    }

    public static String replaceStrForDB(String encrypt_str)
    {
        String return_str = encrypt_str;
        int start_idx = 0;
        int find_idx = -2;
        StringBuffer strBuf = new StringBuffer(100);
        String[][] replace_arr = new String[1][2];
        replace_arr[0][0] = "'";
        replace_arr[0][1] = "''";

        for (int i=0; i < replace_arr.length; i++)
        {
            find_idx = -2;
            while (find_idx != -1)
            {
                find_idx = return_str.indexOf(replace_arr[i][0],start_idx);
                if (find_idx != -1)
                {
                    start_idx = find_idx + replace_arr[i][1].length();
                    strBuf.delete(0, strBuf.length());
                    strBuf.append(return_str.substring(0,find_idx));
                    strBuf.append(replace_arr[i][1]);
                    strBuf.append(return_str.substring(find_idx+replace_arr[i][0].length()));
                    return_str = strBuf.toString();
                }
            }
        }
        return return_str;
    }

}
