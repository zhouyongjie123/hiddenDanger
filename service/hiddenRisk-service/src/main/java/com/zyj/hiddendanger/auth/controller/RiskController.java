package com.zyj.hiddendanger.auth.controller;

import com.zyj.hiddendanger.auth.service.HiddenRiskService;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskPageQueryDTO;
import com.zyj.hiddendanger.web.vo.PageResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/risk")
public class RiskController {
    private final HiddenRiskService hiddenRiskService;

    @PostMapping("/page")
    public PageResponseResult<HiddenRiskVO> page(@RequestBody HiddenRiskPageQueryDTO hiddenRiskPageQueryDTO) {
        return PageResponseResult.ok(hiddenRiskService.page(hiddenRiskPageQueryDTO));
    }
}
