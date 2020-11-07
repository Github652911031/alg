package com.zy.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原ip地址
 * 输入: "25525511135"
    输出: ["255.255.11.135", "255.255.111.35"]
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 */
public class RestoreIpAddresses {
    public static void main(String[] args) {

    }

    public List<String> restoreIpAddress(String s) {
        List<String> result = new ArrayList<>();
        doRestoreIp(result, s, 0, 0, new StringBuilder());
        return result;
    }

    private void doRestoreIp(List<String> result, String s, int start, int k, StringBuilder sb) {

        if(k == 4 || start == s.length()) {
            if(k == 4 && start == s.length()) {
                result.add(sb.toString());
            }
            return;
        }

        for(int i=start;i<s.length() && i <= start + 2;i++) {
            //不能以0开头，但是可以单独是0
            if(i != start && s.charAt(start) == '0') {
                return;
            }
            String part = s.substring(start, i+1);
            if(Integer.parseInt(part) <= 255) {
                if(sb.length() > 0) {
                    part = "." + part;
                }
                sb.append(part);
                doRestoreIp(result, s, i+1, k + 1, sb);
                sb.delete(sb.length() - part.length() , sb.length());
            }

        }
    }
}
