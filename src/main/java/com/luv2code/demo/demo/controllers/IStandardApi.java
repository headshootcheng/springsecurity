package com.luv2code.demo.demo.controllers;

import com.luv2code.demo.demo.configs.SwaggerConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "BEARER_TOKEN")
@Validated
public interface IStandardApi
{
}
