package com.example.socialmedia.Util;


import com.example.socialmedia.Security.AppUserDto;
import com.example.socialmedia.Service.AdminService.AdminService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private AdminService adminService;

    @Override
    public void run(String... strings) throws Exception {

        if (adminService.findAll().isEmpty()) {
            logger.info("No Users accounts found. Creating some users");


            AppUserDto Admin = AppUserDto.builder()
                    .registerDate(new Date())
                    .email("admin@gmail.com")
                    .phone("0592101558")
                    .firstName("admin")
                    .lastName("admin")
                    .role("ADMIN")
                    .password("admin")
                    .gender("male")
                    .title("hello world")
                    .build();

            adminService.save(Admin);
        }
    }
}