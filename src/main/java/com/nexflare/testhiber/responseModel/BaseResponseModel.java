package com.nexflare.testhiber.responseModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponseModel<K> {
    K response;
    int code;
    String errorMessage;
}
