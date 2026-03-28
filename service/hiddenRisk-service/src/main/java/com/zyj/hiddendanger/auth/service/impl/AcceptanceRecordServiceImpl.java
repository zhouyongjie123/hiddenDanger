package com.zyj.hiddendanger.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.model.domain.AcceptanceRecord;
import com.zyj.hiddendanger.auth.service.AcceptanceRecordService;
import com.zyj.hiddendanger.auth.mapper.AcceptanceRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class AcceptanceRecordServiceImpl extends ServiceImpl<AcceptanceRecordMapper, AcceptanceRecord>
    implements AcceptanceRecordService{

}




