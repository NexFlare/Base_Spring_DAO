package com.nexflare.testhiber.requestModel.User;

import com.nexflare.testhiber.requestModel.AbstractRequestObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRequestObject extends AbstractRequestObject {

    private UUID id;
}
