/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.social.connect.ConnectionRepository;

/**
 *
 * @author michaelfouche
 */
@Controller("HelloController")
public class HelloController {
    ConnectionRepository connectionRepository;
    StreamService streamService;
    
    @RequestMapping("/stream/{time}")
    public String streamTweet(@PathVariable int time, Model model) throws InterruptedException{
    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
        return "redirect:/connect/twitter";
    }
    Model returnedmodel = streamService.streamApi(model, time);
    model = returnedmodel;

    return "stream";
}
}
