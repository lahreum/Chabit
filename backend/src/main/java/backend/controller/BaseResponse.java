package backend.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class BaseResponse<T> {
    @ApiModelProperty(value = "요청 처리 성공 여부")
    private String status;
    private T data;
}
