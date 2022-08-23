package org.tholv.SecurityUtils;

public class IpAddressUtils {
    public static IpAddressUtils getInstance(){
        return new IpAddressUtils();
    }
    public synchronized static boolean checkIpAddress(String ipAddress){
        if(ipAddress.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
            return true;
        }
        throw new IllegalArgumentException("Ip address is not valid");
    }

}
