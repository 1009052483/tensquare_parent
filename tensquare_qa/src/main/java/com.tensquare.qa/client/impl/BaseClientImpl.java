package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * Created by XSH on 2018-12-8.
 */
@Component
public class BaseClientImpl implements BaseClient {
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR, "熔断器启动了");
    }
}
