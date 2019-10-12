package com.example.demo;

import java.util.List;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
/**
 * @author woniu
 * @date 2019/10/12 16:20
 */
public class CustomerLoadBalanceRule extends AbstractLoadBalancerRule {

    private static final Log log = LogFactory.getLog(CustomerLoadBalanceRule.class);

    @Override
    public Server choose(Object key) {
        log.info("key is " + key);
        List<Server> servers = this.getLoadBalancer().getReachableServers();
        log.info("servers " + servers);
        if (servers.isEmpty()) {
            return null;
        }
        if (servers.size() == 1) {
            return servers.get(0);
        }
        return randomChoose(servers);
    }
    /**
     *
     * <p>随机返回一个服务实例 </p>
     * @param servers 服务列表
     */
    private Server randomChoose(List<Server> servers) {
        int randomIndex = RandomUtils.nextInt(servers.size());
        return servers.get(randomIndex);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}