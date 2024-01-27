package com.example.Fiaraamidy.controlleur;

import com.example.Fiaraamidy.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.Fiaraamidy.entites.Message;

import java.util.List;

@RestController
@RequestMapping(path="message")
@AllArgsConstructor
public class MessageController {
    private MessageService messageService;

    @GetMapping(path="get")
    public List<Message> get(){
        return this.messageService.getAll();
    }

    @GetMapping(path="discu/{user1}/{user2}")
    public List<Message> getDiscu(@PathVariable int user1,@PathVariable int user2){
        return this.messageService.getDiscu(user1,user2);
    }

    @PostMapping(path="insert")
    public String insert(@RequestBody Message message){
        try{
            this.messageService.insert(message);
            return "Message envoye";
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
