package com.zyj.hiddendanger.flow.controller;

import com.zyj.hiddendanger.flow.service.HiddenRiskStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/risk")
public class HiddenRiskFlowController {
    private final HiddenRiskStreamService hiddenRiskStreamService;
}
