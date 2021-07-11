package netty.outBound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import netty.filter.HeaderHttpResponseFilter;
import netty.filter.HttpRequestFilter;
import netty.filter.HttpResponseFilter;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutBoundHandler  {
    // 缓存客户端实例
    private OkHttpClient client = new OkHttpClient();
    private List<String>  serverUrl;
    private ExecutorService proxyService;//线程池
    private HttpResponseFilter filter = new HeaderHttpResponseFilter();
    public HttpOutBoundHandler(List<String>  serverUrl){
            this.serverUrl = serverUrl;
            prepare();
    }

    private void prepare(){
        this.serverUrl = serverUrl.stream().map(this::formatUrl).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }
    // GET 调用
    public  void handle( HttpRequestFilter filter, FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx,String router) throws IOException {
       // String router = serverUrl.get(new Random().nextInt(serverUrl.size()));

        filter.filter(fullHttpRequest, ctx);
        proxyService.submit(()->fetchGet(fullHttpRequest, ctx, router));

    }

    private void fetchGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx,String router){


        Request.Builder builder = new Request.Builder();
        builder.addHeader("shadiao",fullHttpRequest.headers().get("shadiao"));//header
        Request request = builder.url(router).build();

        client.newCall(request).enqueue(new  Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("回调失败");
            }

            @Override
            public void onResponse(Call call,  Response response) throws IOException {
                System.out.println("fetchGet "+response.body().toString());
                handleResponse(fullHttpRequest,ctx,response);
            }
        });
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse)   {
        FullHttpResponse response = null;
        try {
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());


            ResponseBody body = endpointResponse.body();
            System.out.println("body");
            System.out.println(endpointResponse.message());
//            System.out.println(body.length);

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body.bytes()));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length",(int) body.contentLength());

            System.out.println("response "+response);

            filter.filter(response);

//            for (Header e : endpointResponse.getAllHeaders()) {
//                //response.headers().set(e.getName(),e.getValue());
//                System.out.println(e.getName() + " => " + e.getValue());
//            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            //exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }

    }


}
