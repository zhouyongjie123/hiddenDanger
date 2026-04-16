package com.zyj.hiddendanger.flow.controller;

import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.UnImplementationExceptionCode;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/process")
public class FlowController {

    @PostMapping("/create")
    public ResponseResult<?> createProcessByGraph() {
        throw new SystemException(UnImplementationExceptionCode.METHOD_UNIMPLEMENT);
    }
}
