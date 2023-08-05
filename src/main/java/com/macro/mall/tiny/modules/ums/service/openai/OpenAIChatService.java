package com.macro.mall.tiny.modules.ums.service.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.macro.mall.tiny.modules.ums.service.openai.request.ChatRequest;
import com.macro.mall.tiny.util.HttpUtils;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.FunctionExecutor;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.macro.mall.tiny.modules.ums.service.openai.OpenaiService.WeatherUnit.CELSIUS;

/**
 * 后台用户缓存管理Service实现类
 * Created by macro on 2020/3/13.
 */
@Service
@Slf4j
public class OpenAIChatService {


    public String chatByHttp(ChatRequest chatRequest) {
        String content = chatRequest.getContent();

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", "Bearer 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");

        String url = "https://api.openai.com/v1/chat/completions";
        String result = HttpUtils.postJson(url, header, "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": " + content + "}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }");
        log.info("OpenAIChatService#chatByHttp.result:" + result);
        System.out.println(result);
        return result;
    }


}
