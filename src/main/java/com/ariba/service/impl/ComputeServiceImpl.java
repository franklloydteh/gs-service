package com.ariba.service.impl;

import com.ariba.repo.EnvelopeRepo;
import com.ariba.service.ComputeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Frank Lloyd Teh
 */
@Service("computeService")
public class ComputeServiceImpl implements ComputeService {


    @Resource(name = "envelopeRepo")
    private EnvelopeRepo envelopeRepo;

    @Override
    public Integer add(int a, int b) {
        return a + b;
    }
}
