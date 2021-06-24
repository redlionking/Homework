package jvm;

import java.lang.reflect.Method;
import java.util.Base64;

public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception {

        Object helloClass = new MyClassLoader().findClass("Hello").getDeclaredConstructor().newInstance();
        Method method = helloClass.getClass().getMethod("hello");
        method.setAccessible(true);
        method.invoke( helloClass);


    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //xclass的Base64编码
        String xclass = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfW" +
                "qf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+" +
                "//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74" +
                "/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCs" +
                "hoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+N" +
                "lpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/" +
                "+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v//" +
                "//7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////" +
                "+//3//r//v/z/////f/y";
        byte[] bytes = decode(xclass);
        byte[] subcode = subcode(bytes);
        return  defineClass(name, subcode,0, subcode.length);
    }

    public byte[] decode(String xclass){
        return Base64.getDecoder().decode(xclass);
    }

    public byte[] subcode(byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }

}
