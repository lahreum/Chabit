package backend.controller;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String status; // 요청 처리 성공 여부
    private T data;
}
