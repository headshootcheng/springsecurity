package com.luv2code.demo.demo.modals.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthMessageResponse {
    private Integer status;
    private String message;
    private long timeStamp;
    private boolean isError;

}
