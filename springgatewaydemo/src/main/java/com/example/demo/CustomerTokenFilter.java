package com.example.demo;

import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author woniu
 * @date 2019/10/11 16:07
 */
@Component
public class CustomerTokenFilter implements GlobalFilter, Ordered{

    private static final Log log = LogFactory.getLog(GatewayFilter.class);

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        log.info("contain token " + exchange.getRequest().getHeaders().containsKey("token"));
        log.info("token is " + exchange.getRequest().getHeaders().get("token"));
        if (exchange.getRequest().getPath().value().contains("success") || exchange.getRequest().getPath().value()
                .contains("rest")) {
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                        if (startTime != null) {
                            log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                        }
                    })
            );
        } else {
            byte[] bytes =
                    "{\"status\":429,\"msg\":\"Too Many Requests\",\"data\":{}}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            serverHttpResponse.setStatusCode(HttpStatus.OK);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
