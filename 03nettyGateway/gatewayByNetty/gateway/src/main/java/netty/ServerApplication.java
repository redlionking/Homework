package netty;

import netty.InBound.NettyHttpServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerApplication {
    public static void main(String[] args) throws Exception {
        NettyHttpServer server = new NettyHttpServer();
        List<String> serverlist = new ArrayList<>();
        serverlist.addAll(Arrays.asList(new String[]{"http://127.0.0.1:8801", "http://127.0.0.1:8802"}));
        server.newInstance(serverlist);
    }
}
