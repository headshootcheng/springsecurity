package com.luv2code.demo.demo.modals.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String username;
    private String email;
    private List<String> roles = new ArrayList<>();
}
