package com.maslyna.springemailservice.util.scheduler;

import com.maslyna.springemailservice.repo.DeletedUsersRepository;
import com.maslyna.springemailservice.repo.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@EnableScheduling
@AllArgsConstructor
public class DeleteUsersScheduler {
    UserEntityRepository userEntityRepository;
    DeletedUsersRepository deletedUsersRepository;


    @Scheduled(fixedRate = 180000) // every 3 min
    public void runTask() {
        deletedUsersRepository.findAll()
                .forEach(user -> {
                    if (user.getTimeToDelete().isAfter(Instant.now())) {
                        deletedUsersRepository.delete(user);
                        userEntityRepository.deleteById(user.getDeletedUser().getId());
                    }
                });
    }
}