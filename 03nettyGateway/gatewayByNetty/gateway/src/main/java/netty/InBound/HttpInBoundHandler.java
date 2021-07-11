package netty.InBound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import netty.filter.HeaderHttpRequestFilter;
import netty.outBound.HttpOutBoundHandler;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpInBoundHandler extends ChannelInboundHandlerAdapter {

    private HttpOutBoundHandler httpOutBoundHandler;
    private List<String> serverlist;

    public HttpInBoundHandler(List<String> serverlist){
        this.serverlist = serverlist;
        httpOutBoundHandler = new HttpOutBoundHandler(serverlist);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            //logger.info("接收到的请求url为{}", uri);
            if (uri.contains("/8801")) {
                httpOutBoundHandler.handle(new HeaderHttpRequestFilter(),fullRequest, ctx,serverlist.get(0) );
            } else if(uri.contains("/8802")){
                httpOutBoundHandler.handle(new HeaderHttpRequestFilter(),fullRequest, ctx,serverlist.get(1) );
            }else
            {
                back(fullRequest, ctx, "hello,others");
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void back(FullHttpRequest fullRequest, ChannelHandlerContext ctx,String value){
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
        }catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
                ctx.flush();
            }
        }
    }

//    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String url) {
//        FullHttpResponse response = null;
//        try {
//
//            String value =null; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
//            if("hello,others".equals(url)){
//                value="hello,others";
//            }else{
//                value = HttpOutBoundHandler.getAsString(url);
//            }
////            httpGet ...  http://localhost:8801
////            返回的响应，"hello,nio";
////            value = reponse....
//
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());
//
//        } catch (Exception e) {
//            System.out.println("处理出错:"+e.getMessage());
//            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
//        } finally {
//            if (fullRequest != null) {
//                if (!HttpUtil.isKeepAlive(fullRequest)) {
//                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//                } else {
//                    response.headers().set(CONNECTION, KEEP_ALIVE);
//                    ctx.write(response);
//                }
//                ctx.flush();
//            }
//        }
//    }

    //@Override
    //public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    //    cause.printStackTrace();
    //    ctx.close();
    //}

}
