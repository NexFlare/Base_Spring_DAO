package com.nexflare.testhiber.responseModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponseModel<K> extends Response{
    K response;
    int code;
    String errorMessage;
}
