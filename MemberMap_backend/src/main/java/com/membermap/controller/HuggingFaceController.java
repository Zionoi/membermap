package com.membermap.controller;

import com.membermap.service.HuggingFaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/huggingface")
public class HuggingFaceController {

    private final HuggingFaceService huggingFaceService;

    public HuggingFaceController(HuggingFaceService huggingFaceService) {
        this.huggingFaceService = huggingFaceService;
    }

    /**
     * 클라이언트로부터 입력 텍스트를 받아 bert-base-uncased 모델 호출 결과 반환
     *
     * 요청 예시:
     * {
     *   "input": "이 제품에 대해 어떻게 생각하세요?"
     * }
     *
     * @param request 요청 바디 (JSON 형식)
     * @return 모델 호출 결과 JSON
     */
    @PostMapping("/call")
    public ResponseEntity<?> callBertModel(@RequestBody Map<String, String> request) {
        String inputText = request.get("input");
        if (inputText == null || inputText.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("입력 텍스트(input)는 필수입니다.");
        }
        // WebClient는 reactive 방식이므로, 간단한 예제로 동기 처리(block) 사용
        String result = huggingFaceService.callBertModel(inputText).block();
        return ResponseEntity.ok(result);
    }
}

