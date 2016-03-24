package com.ariba.controller;

import com.ariba.dto.GreetingDto;
import com.ariba.dto.GreetingListDto;
import com.ariba.model.Greeting;
import com.ariba.repo.GreetingRepo;
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

    @RequestMapping(value = "/hello/{id}", method = GET)
    public GreetingDto get(@PathVariable Long id) {
        Greeting greeting = greetingRepo.findOne(id);
        GreetingDto dto = new GreetingDto();
        BeanUtils.copyProperties(greeting, dto);
        return dto;
    }

    @RequestMapping(value = "/hello", method = GET)
    public List<GreetingListDto> list() {
        List<GreetingListDto> dtos = new ArrayList<>();
        List<Greeting> greetings = greetingRepo.findAll();
        for (Greeting greeting : greetings) {
            GreetingListDto dto = new GreetingListDto();
            BeanUtils.copyProperties(greeting,dto);
            dtos.add(dto);
        }
        return dtos;
    }

    @RequestMapping(value = "/hello", method = POST)
    public GreetingDto save(@RequestBody GreetingDto dto) {
        Greeting greeting = new Greeting();

        BeanUtils.copyProperties(dto, greeting, "password", "excludeMeToo");
        Greeting saved = greetingRepo.save(greeting);

        BeanUtils.copyProperties(saved, dto);
        return dto;
    }

    @RequestMapping(value = "/hello/{id}", method = DELETE)
    public void delete(@PathVariable Long id) {
        greetingRepo.delete(id);
    }

}