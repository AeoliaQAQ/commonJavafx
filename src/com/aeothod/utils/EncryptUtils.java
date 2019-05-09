package com.aeothod.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class EncryptUtils {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("密码：z*aaaahkafak");
        String encrypt=encrypt("z*aaaahkafak");
        System.out.println("加密后密码："+encrypt);
        
        String psd=decrypt(encrypt);
        System.out.println("解密后密码："+psd);
    }
    /**
     * @description: 加密
     * @return
     */
    public static String encrypt(String password) {
        if(BussinessUtils.isEmpty(password)) {
            return "";
        }
        int length=password.length();
        int index=random9();
        String fontPsd="";
        if(index>=length-1) {//如果随机数(index)超过密码长度
            fontPsd=password.substring(length-1);
            password=password.substring(0,length-1);
        }else {
           fontPsd=password.substring(index,index+1);
           String temp=password.substring(0,index);
           String temp2=password.substring(index+1);
           password=temp+temp2;
        }
        String length16=strTo16(String.valueOf(length));
        length16=String.format("%04d", Integer.valueOf(length16));
        fontPsd=length16+index+fontPsd;
        String str=strTo16(password);
        String addRandom=addRandom(str);
        String[] rows=split2Row(addRandom);
        String joinRow=joinRow(rows);
        return fontPsd+joinRow;
    }
    public static String decrypt(String decryptPsd) {
        if(BussinessUtils.isEmpty(decryptPsd)) {
            return "";
        }
        String fontPsd=decryptPsd.substring(0,6);//前6位
        //拆出前6位
        decryptPsd=decryptPsd.substring(6);
        //还原成两行
        String[] decryStrings=returnRow(decryptPsd);
        //还原分割
        decryptPsd=decryStrings[0]+decryStrings[1];
        decryptPsd=wipeRandom(decryptPsd);
        decryptPsd=hexToStr(decryptPsd);
        int length=0;
        if(fontPsd.substring(0,2).equals("00")) {
            length=Integer.valueOf(hexToStr(fontPsd.substring(2,4)));
        }else {
            length=Integer.valueOf(hexToStr(fontPsd.substring(0,4)));
        }
        int index=Integer.valueOf(fontPsd.substring(4,5));
        String suppleStr=fontPsd.substring(5);
        decryptPsd=supple(decryptPsd, length, index, suppleStr);
                
        return decryptPsd;
    }
    /**
     * @description: 补值
     * @param psd
     * @return
     */
    public static String supple(String psd,int length,int index,String suppleStr) {
        if(index==0) {
            return suppleStr+psd;            
        }else if(index>length-1) {//如果index大于密码
            return psd+suppleStr;
        }else {
            String temp=psd.substring(0, index);
            String temp1=psd.substring(index);
            return temp+suppleStr+temp1;
        }
    }
    /**
     * @description: 去除随机数
     * @param psd
     * @return
     */
    public static String wipeRandom(String psd) {
        boolean isWipe=false;
        String[] wipePsd=psd.split("");
        int length=wipePsd.length;
        StringBuilder sb=new StringBuilder(20);
        for(int i=0;i<length;i++) {
            if(!isWipe) {
                sb.append(wipePsd[i]);
            }
            isWipe=!isWipe;
        }
        return sb.toString();
        
    }
    /**
     * @description: 还原成两行
     * @param psd
     * @return
     */
    public static String[] returnRow(String psd) {
        String[] rows=new String[2];
        StringBuilder sb1=new StringBuilder(20);
        StringBuilder sb2=new StringBuilder(20);
        boolean isFirstRow=true;
        String[] tempList=psd.split("");
        int tempLength=tempList.length;
        for(int i=0;i<tempLength;i++) {
            if(i!=tempLength-1) {
                if(isFirstRow) {
                    sb1.append(tempList[i]);
                }else {
                    sb2.append(tempList[i]);
                }
                isFirstRow=!isFirstRow;
            }else {//末尾
                sb2.append(tempList[i]);
            }
        }
        rows[0]=sb1.toString();
        rows[1]=sb2.toString();
        return rows;
    }
    /**
     * @description: 随机一个不大于9的int数(0-8)
     * @return
     */
    private static int random9() {
        return new Random().nextInt(9);
    }
    /**
     * @description: 字符串转化成16进制数
     * @param in
     * @return
     */
    private static String strTo16(String in) {
        //hnd19147
        StringBuilder sb=new StringBuilder(50);
        for(char c:in.toCharArray()) {
            sb.append(Integer.toHexString(c));
        }
        return sb.toString();
    }
    /**
     * @description: 产生随机16进制数
     * @return
     */
    private static String random16() {
        int i=new Random().nextInt(15);
        return Integer.toHexString(i).toUpperCase();
    }
    /**
     * @description: 间隙加入随机16位进制数
     * @param in
     * @return
     */
    private static String addRandom(String in) {
        StringBuilder sb=new StringBuilder(50);
        String[] inSplit=in.split("");
        for(int i=0;i<inSplit.length;i++) {
            if(i!=inSplit.length-1) {
                sb.append(inSplit[i]).append(random16());
            }else {
                sb.append(inSplit[i]);
            }
        }
        return sb.toString();
    }
    /**
     * @description: 分割成两行(余数在第二行)
     * @param in
     * @return
     */
    private static String[] split2Row(String in){
        String[] rows=new String[2];
        int oneLength=in.length()/2;
        rows[0]=in.substring(0, oneLength);
        rows[1]=in.substring(oneLength);
        return rows;
    }
    /**
     * @description: 两行错位插入
     * @param in
     * @return
     */
    private static String joinRow(String[] in) {
        StringBuilder sb=new StringBuilder(50);
        String mod=in[1].substring(in[0].length());
//        System.out.println("余数:"+mod);
        char[] row1=in[0].toCharArray();
        char[] row2=in[1].toCharArray();
        for(int i=0;i<in[0].length();i++) {
            sb.append(row1[i]).append(row2[i]);
        }
        sb.append(mod);
        return sb.toString();
    }
    /**
     * @description: 16进制转成字符串
     * @param in
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String hexToStr(String in) {
        char[] charIn=in.toCharArray();
        byte[] byteIn=new byte[charIn.length/2];
        for(int i=0;i<byteIn.length;i++) {
            byteIn[i] = (byte) (0xff & Integer.parseInt(in.substring(i * 2, i * 2 + 2), 16));
        }
        return new String(byteIn);
    }
}
