package com.ariba.controller;

import com.ariba.dto.EnvelopeDto;
import com.ariba.dto.EnvelopeGreetingsDto;
import com.ariba.dto.GreetingListDto;
import com.ariba.model.Envelope;
import com.ariba.model.Greeting;
import com.ariba.repo.EnvelopeRepo;
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
public class EnvelopeController {

    @Resource(name = "envelopeRepo")
    private EnvelopeRepo envelopeRepo;

    @RequestMapping(value = "/envelope/{id}", method = GET)
    public EnvelopeDto get(@PathVariable Long id) throws InterruptedException {
        Envelope envelope = envelopeRepo.findOne(id);
        EnvelopeDto dto = new EnvelopeDto();
        BeanUtils.copyProperties(envelope, dto);
        return dto;
    }

    @RequestMapping(value = "/envelope/{id}/greetings", method = GET)
    public EnvelopeGreetingsDto getGreetings(@PathVariable Long id) throws InterruptedException {
        Envelope envelope = envelopeRepo.findOne(id);
        EnvelopeGreetingsDto dto = new EnvelopeGreetingsDto();
        BeanUtils.copyProperties(envelope, dto, "greetings");

        for (Greeting greeting : envelope.getGreetings()) {
            GreetingListDto gld = new GreetingListDto();
            BeanUtils.copyProperties(greeting, gld);
            dto.getGreetings().add(gld);
        }

        return dto;
    }

    @RequestMapping(value = "/envelope/search/{name}/greetings", method = GET)
    public List<GreetingListDto> getGreetings(@PathVariable String name) throws InterruptedException {
        Envelope envelope = envelopeRepo.findByName(name);
        List<GreetingListDto> dtos = new ArrayList<>();

        for (Greeting greeting : envelope.getGreetings()) {
            GreetingListDto gld = new GreetingListDto();
            BeanUtils.copyProperties(greeting, gld);
            dtos.add(gld);
        }

        return dtos;
    }

    @RequestMapping(value = "/envelope", method = GET)
    public List<EnvelopeDto> list() throws InterruptedException {
        //Thread.sleep(5000);
        List<EnvelopeDto> dtos = new ArrayList<>();
        List<Envelope> envelopes = envelopeRepo.findAll();
        for (Envelope envelope : envelopes) {
            EnvelopeDto dto = new EnvelopeDto();
            BeanUtils.copyProperties(envelope, dto);
            dtos.add(dto);
        }
        return dtos;
    }

    @RequestMapping(value = "/envelope", method = POST)
    public EnvelopeDto save(@RequestBody EnvelopeDto dto) {
        Envelope envelope = dto.getId() == null ? new Envelope() : envelopeRepo.findOne(dto.getId());

        BeanUtils.copyProperties(dto, envelope, "password", "excludeMeToo");
        Envelope saved = envelopeRepo.save(envelope);

        BeanUtils.copyProperties(saved, dto);
        return dto;
    }

    @RequestMapping(value = "/envelope/{id}", method = DELETE)
    public void delete(@PathVariable Long id) {
        envelopeRepo.delete(id);
    }

}
