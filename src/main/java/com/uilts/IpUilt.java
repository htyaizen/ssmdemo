package com.uilts;

import javax.management.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/19.
 */
public class IpUilt {
    public static void main(String[] args) {
        try {
            System.out.println(getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取本地IP地址
     * @return
     * @throws UnknownHostException
     */
    public static String getHostAddress() throws UnknownHostException {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    InetAddress ip = ips.nextElement();
                    if (ip.isSiteLocalAddress()) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()+"    "+e.toString());
        }
        return InetAddress.getLocalHost().getHostAddress();
    }
}
