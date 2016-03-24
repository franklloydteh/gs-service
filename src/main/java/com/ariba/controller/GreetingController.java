package com.ariba.controller;

import com.ariba.dto.GreetingDto;
import com.ariba.dto.GreetingListDto;
import com.ariba.model.Envelope;
import com.ariba.model.Greeting;
import com.ariba.repo.EnvelopeRepo;
import com.ariba.repo.GreetingRepo;
import com.ariba.service.ComputeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Frank Lloyd Teh
 */
@RestController
public class GreetingController {

    @Resource(name = "greetingRepo")
    private GreetingRepo greetingRepo;

    @Resource(name = "envelopeRepo")
    private EnvelopeRepo envelopeRepo;

    @Resource(name = "computeService")
    private ComputeService computeService;

    @RequestMapping(value = "/hello/{id}", method = GET)
    public GreetingDto get(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(5000);
        Greeting greeting = greetingRepo.findOne(id);
        GreetingDto dto = new GreetingDto();
        BeanUtils.copyProperties(greeting, dto);
        return dto;
    }

    @RequestMapping(value = "/hello", method = GET)
    public List<GreetingListDto> list() throws InterruptedException {
        //Thread.sleep(5000);
        List<GreetingListDto> dtos = new ArrayList<>();
        List<Greeting> greetings = greetingRepo.findAll();
        for (Greeting greeting : greetings) {
            GreetingListDto dto = new GreetingListDto();
            BeanUtils.copyProperties(greeting, dto);
            dtos.add(dto);
        }

        Integer result = computeService.add(1, 3);
        System.out.println("Computing: " + result);

        return dtos;
    }

    @RequestMapping(value = "/hello", method = POST)
    public GreetingDto save(@RequestBody GreetingDto dto) {
        Greeting greeting = dto.getId() == null ? new Greeting() : greetingRepo.findOne(dto.getId());

        BeanUtils.copyProperties(dto, greeting);

        Envelope envelope = dto.getEnvelopeId() == null ? null : envelopeRepo.findOne(dto.getEnvelopeId());
        greeting.setEnvelope(envelope);
        Greeting saved = greetingRepo.save(greeting);

        BeanUtils.copyProperties(saved, dto);
        return dto;
    }

    @RequestMapping(value = "/hello/{id}", method = DELETE)
    public void delete(@PathVariable Long id) {
        greetingRepo.delete(id);
    }

}
