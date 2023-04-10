package com.nexflare.testhiber.requestModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetByIdRequestObject extends AbstractRequestObject {

    private UUID id;
}
