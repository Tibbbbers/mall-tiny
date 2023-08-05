package com.macro.mall.tiny.modules.ums.service.openai;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macro.mall.tiny.common.service.RedisService;
import com.macro.mall.tiny.modules.ums.mapper.UmsAdminMapper;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.model.UmsAdminRoleRelation;
import com.macro.mall.tiny.modules.ums.model.UmsResource;
import com.macro.mall.tiny.modules.ums.service.UmsAdminCacheService;
import com.macro.mall.tiny.modules.ums.service.UmsAdminRoleRelationService;
import com.macro.mall.tiny.modules.ums.service.UmsAdminService;
import com.macro.mall.tiny.util.HttpUtils;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.FunctionExecutor;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.macro.mall.tiny.modules.ums.service.openai.OpenaiService.WeatherUnit.CELSIUS;

/**
 * 后台用户缓存管理Service实现类
 * Created by macro on 2020/3/13.
 */
@Service
public class OpenaiService {


    public boolean useai() {

        OpenAiService service = new OpenAiService("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");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);


        return true;
    }

    public static void main(String[] args) {

        OpenAiService service = new OpenAiService("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");
//        CompletionRequest completionRequest = CompletionRequest.builder()
//                .prompt("今天是哪一天？")
//                .model("gpt-3.5-turbo")
//                .echo(true)
//                .build();

//        List<ChatMessage> messages = new ArrayList<>();
//        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), "写一篇春游文章");
//        messages.add(userMessage);
//
//        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
//                .model("gpt-3.5-turbo")
//                .messages(messages)
//                .build();
//
//        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(System.out::println);


        Map<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Authorization","Bearer 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");




        String url = "https://api.openai.com/v1/chat/completions";
        String result= HttpUtils.postJson(url,header,"{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"你好，你是谁\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }");
        System.out.println(result);




//        ChatFunction function = ChatFunction.builder()
//                .name("get_weather")
//                .description("Get the current weather of a location")
//                .executor(Weather.class, w -> new WeatherResponse("beijing", CELSIUS, new Random().nextInt(50), "sunny"))
//                .build();
//
//        List<ChatFunction> functionList = new ArrayList<>();// list with functions
//        functionList.add(function);
//        FunctionExecutor functionExecutor = new FunctionExecutor(functionList);
//
//        List<ChatMessage> messages = new ArrayList<>();
//        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), "Tell me the weather in Barcelona.");
//        messages.add(userMessage);
//        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
//                .builder()
//                .model("gpt-3.5-turbo-0613")
//                .messages(messages)
//                .functions(functionExecutor.getFunctions())
//                .functionCall(new ChatCompletionRequest.ChatCompletionRequestFunctionCall("auto"))
//                .maxTokens(256)
//                .build();
//
//        ChatMessage responseMessage = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();
//        ChatFunctionCall functionCall = responseMessage.getFunctionCall(); // might be null, but in this case it is certainly a call to our 'get_weather' function.
//
//        ChatMessage functionResponseMessage = functionExecutor.executeAndConvertToMessageHandlingExceptions(functionCall);
////        messages.add(response);
//        System.out.printf("aaaa");
////        System.out.printf("aaa" + JSONUtils.toJSONString(functionResponseMessage));

    }

    public static class Weather {
        @JsonPropertyDescription("City and state, for example: León, Guanajuato")
        public String location;
        @JsonPropertyDescription("The temperature unit, can be 'celsius' or 'fahrenheit'")
        @JsonProperty(required = true)
        public WeatherUnit unit;
    }

    public enum WeatherUnit {
        CELSIUS, FAHRENHEIT;
    }

    public static class WeatherResponse {
        public String location;
        public WeatherUnit unit;
        public int temperature;
        public String description;

        // constructor

        public WeatherResponse(String location, WeatherUnit unit, int temperature, String description) {
            this.location = location;
            this.unit = unit;
            this.temperature = temperature;
            this.description = description;
        }
    }


}
