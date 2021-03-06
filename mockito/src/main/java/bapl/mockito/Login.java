package bapl.mockito;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams; 

public class Login {
	
	
	public void test() throws HttpException, IOException{  
        //String url = "http://jf.ccb.com/exchangecenter/login/checkRandomCodeForAjax.jhtml";  
        String url= "http://jf.ccb.com/exchangecenter/RandomCodeAction/makeImageCodeForCust.jhtml?q="+new Date().getTime();
        HttpClient httpClient = new HttpClient();  
        DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);  
        GetMethod getMethod = new GetMethod(url);    
        getMethod.setRequestHeader("Host", "jf.ccb.com");  
        getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.2; rv:5.0.1) Gecko/20100101 Firefox/5.0.1");  
        getMethod.setRequestHeader("Accept", "image/webp,image/*,*/*;q=0.8");  
        getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");  
        getMethod.setRequestHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");  
        getMethod.setRequestHeader("Connection", "keep-alive");  
        getMethod.setRequestHeader("Referer","http://jf.ccb.com/exchangecenter/login/showLogin.jhtml");
//      getMethod.setRequestHeader("Cookie", "ck_lg_reb_user_account=bapl1016; ck_lg_reb_type=0; JSESSIONID=lJjFRRG51WX2NHj10yNNaFuuVCrxu_L0gedMXE7fLp4Eufdwf1EO!1562361083");  
        getMethod.setRequestHeader("Cache-Control", "max-age=0");  
        int code = httpClient.executeMethod(getMethod);  
        Header header = getMethod.getResponseHeader("Set-cookie");   
        System.out.println(header.getValue());  
        String headerCookie = header.getValue();  
        String SessionId = headerCookie.substring(headerCookie.indexOf("NET_SessionId=") + "NET_SessionId=".length(), headerCookie.indexOf(";"));  
        System.out.println(SessionId);  
        String BIGipServernxt = headerCookie.substring(headerCookie.indexOf("BIGipServernxt-wz=") + "BIGipServernxt-wz=".length(), headerCookie.length());  
        BIGipServernxt = BIGipServernxt.substring(0, BIGipServernxt.indexOf(";"));  
        StringBuffer sb = new StringBuffer();  
        sb.append("Hm_lvt_6e06bb5a029d6c5473951d1079638828=1328778307106; ");  
        sb.append("Hm_lvt_e64244e1e591d0337e17a12b714c0996=1328778308090; ");  
        sb.append("WT_FPC=id=183.16.35.230-1530895312.30204303:lv=1328174810886:ss=1328174810886; ");  
        sb.append("ASP.NET_SessionId=").append(SessionId).append("; ");  
        sb.append("BIGipServernxt-wz=").append(BIGipServernxt).append("; ");  
        sb.append("Hm_lpvt_6e06bb5a029d6c5473951d1079638828=1328778307106; ");  
        sb.append("Hm_lpvt_e64244e1e591d0337e17a12b714c0996=1328778308090");  
        System.out.println(sb.toString());  
        String ValidCode = savegif(getMethod);  
        System.out.println("ValidCode:"+ValidCode);  
        getMethod.releaseConnection();  
        // 第二次链接  
        httpClient.getHostConfiguration().setHost("jf.ccb.com", 80, "http");  
        PostMethod method = getPostMethod(ValidCode);  
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.2; rv:5.0.1) Gecko/20100101 Firefox/5.0.1");  
        method.setRequestHeader("Host", "jf.ccb.com");  
        method.setRequestHeader("Accept", "application/json, text/javascript, */*");  
        method.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");  
//      method.setRequestHeader("Accept-Encoding", "gzip, deflate");  
        method.setRequestHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");  
        method.setRequestHeader("Connection", "keep-alive");  
        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
        method.setRequestHeader("X-Requested-With", "XMLHttpRequest");  
        method.setRequestHeader("Referer", "http://jf.ccb.com/exchangecenter/login/showLogin.jhtml");  
        method.setRequestHeader("Cookie", sb.toString());   
        httpClient.executeMethod(method);  
        System.out.println(method.getStatusCode());  
        System.out.println(method.getResponseBodyAsString());  
//        // 第三次链接  
//        Header header1 = method.getResponseHeader("Set-cookie");   
//        System.out.println("dd=" + header1.getValue());  
//        sb.append("; " + header1.getValue());  
//        Cookie[] cookies = httpClient.getState().getCookies();  
//        method.releaseConnection();  
//        String my = "http://12582.10086.cn/my";  
//        System.out.println(sb.toString());  
//        getMethod = new GetMethod(my);  
//        getMethod.setRequestHeader("Host", "12582.10086.cn");  
//        getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.2; rv:5.0.1) Gecko/20100101 Firefox/5.0.1");  
//        getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
//        getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");  
//        getMethod.setRequestHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");  
//        getMethod.setRequestHeader("Connection", "keep-alive");  
//        getMethod.setRequestHeader("Referer", "http://12582.10086.cn/user/login");  
//        getMethod.setRequestHeader("Cookie", sb.toString());  
//        code = httpClient.executeMethod(getMethod);  
//        System.out.println(getMethod.getStatusCode());  
//        System.out.println(getMethod.getResponseBodyAsString());  
//        File storeFile = new File("c:/2008sohu.html");    
//        FileOutputStream output = new FileOutputStream(storeFile);    
//        //得到网络资源的字节数组,并写入文件    
//        output.write(getMethod.getResponseBody());    
//        output.close();   
    }  
      
    public String savegif(GetMethod getMethod) throws IOException {  
        File storeFile = new File("2008sohu.gif");    
        FileOutputStream output = new FileOutputStream(storeFile);    
        //得到网络资源的字节数组,并写入文件    
        output.write(getMethod.getResponseBody());    
        output.close();    
        InputStreamReader is = new InputStreamReader(System.in);   
        BufferedReader br = new BufferedReader(is);  
        String ValidCode = "";  
        try {  
            ValidCode = br.readLine();  
            br.close();  
            is.close();  
        } catch(Exception e) {  
            e.printStackTrace();  
        }  
        return ValidCode;  
    }  
      
    private PostMethod getPostMethod(String ValidCode) {  
        PostMethod post = new PostMethod("http://jf.ccb.com/exchangecenter/login/checkRandomCodeForAjax.jhtml");  
//        NameValuePair[] simcard = {  
//                new NameValuePair("email", "XXX"),  
//                new NameValuePair("password", "XXX"),  
//                new NameValuePair("ValidCode", ValidCode),  
//                new NameValuePair("rme", "0"),  
//                };  
//        post.setRequestBody(simcard);  
        return post;  
    }  
      
    public static void main(String args[]) {  
    	Login _10086 = new Login();  
        try {  
            _10086.test();  
        } catch (HttpException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}  