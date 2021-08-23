package io.kimmking.rpcfx.demo.provider;

import com.sun.tools.sjavac.Log;
import io.kimmking.rpcfx.api.RpcfxResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;//避免加载多次，而是从已有的加载

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        Log.info("applicationContextAware initiate success!!!!!!!!!!!!!!!!");

    }

    @Override
    public Object resolve(String serviceClass) {
        return this.applicationContext.getBean(serviceClass);
    }
}
