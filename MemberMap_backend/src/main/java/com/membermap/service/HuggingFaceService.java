package com.membermap.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class HuggingFaceService {

    private final WebClient webClient;
    private final String modelEndpoint;

    /**
     * 생성자에서 API Key, 기본 URL, 모델명을 주입받아 WebClient 설정
     *
     * @param apiKey       Hugging Face API Key
     * @param apiUrl       Hugging Face API 기본 URL (예: https://api-inference.huggingface.co)
     * @param modelName    사용할 모델명 (예: bert-base-uncased)
     */
    public HuggingFaceService(
            @Value("${huggingface.api.key}") String apiKey,
            @Value("${huggingface.api.url}") String apiUrl,
            @Value("${huggingface.api.model}") String modelName) {

        // 모델 엔드포인트 구성: https://api-inference.huggingface.co/models/{modelName}
        this.modelEndpoint = String.format("/models/%s", modelName);

        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }

    /**
     * 입력 텍스트를 기반으로 bert-base-uncased 모델(예: 감성 분석 혹은 텍스트 분류 태스크) 호출
     *
     * @param inputText 모델에 전달할 텍스트 데이터
     * @return 모델 응답 결과 (JSON 문자열)
     */
    public Mono<String> callBertModel(String inputText) {
        // 요청 본문 구성: 태스크에 맞게 payload 구성 필요
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("inputs", inputText);

        return webClient.post()
                .uri(modelEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}
