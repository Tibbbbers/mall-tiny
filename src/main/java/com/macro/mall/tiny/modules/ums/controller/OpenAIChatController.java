package com.macro.mall.tiny.modules.ums.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.service.openai.OpenAIChatService;
import com.macro.mall.tiny.modules.ums.service.openai.request.ChatRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * openapi
 * Created by tibers on 2018/4/26.
 */
@Controller
@Api(tags = "OpenAIChatController", description = "openapi")
@RequestMapping("/openai")
public class OpenAIChatController {

    @Autowired
    private OpenAIChatService openAIChatService;

    @ApiOperation(value = "聊天")
    @RequestMapping(value = "/chat")
    @ResponseBody
    public CommonResult<String> chatByHttp(@Validated @RequestBody ChatRequest chatRequest) {
        String result = openAIChatService.chatByHttp(chatRequest);
        return CommonResult.success(result);
    }
}
