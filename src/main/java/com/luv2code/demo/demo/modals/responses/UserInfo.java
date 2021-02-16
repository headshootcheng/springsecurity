package com.luv2code.demo.demo.modals.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends RepresentationModel<UserInfo>{
    private Long id;
    private String username;
    private String email;
    private List<String> roles = new ArrayList<>();
}
